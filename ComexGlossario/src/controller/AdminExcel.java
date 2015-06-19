package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Disciplina;
import model.Entregues;
import persistence.DisciplinaDao;
import persistence.ExcelDao;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

@WebServlet("/excel")
public class AdminExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminExcel() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getSession().getServletContext();
		String dir = context.getRealPath(File.separator + "arquivos");
		File arq = new File(dir, "planilha.xls");
		if (arq.exists()) {
			arq.delete();
		}
		arq.createNewFile();
		WritableWorkbook workbook = Workbook.createWorkbook(arq);

		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 2; j++) {
				DisciplinaDao dDao = new DisciplinaDao();
				ExcelDao eDao = new ExcelDao();
				Disciplina d = new Disciplina();
				d.setCodigo(i);
				try {
					d = dDao.consultaDisciplina(d);
					String nomePlanilha = d.getNome();
					if (j == 1) {
						nomePlanilha = nomePlanilha + " - Manhã";
					} else {
						nomePlanilha = nomePlanilha + " - Tarde";
					}
					WritableSheet sheet = workbook.createSheet(nomePlanilha,
							i - 1);
					List<Entregues> listaEntregues = eDao.listaEntregues(i, j);
					int r = 2;
					Label label = new Label(0, 0, nomePlanilha);
					sheet.addCell(label);
					label = new Label(0, 1, "RA");
					sheet.addCell(label);
					label = new Label(1, 1, "NOME");
					sheet.addCell(label);
					label = new Label(2, 1, "Qtd Aprovados");
					sheet.addCell(label);
					label = new Label(3, 1, "Qtd Corrigir");
					sheet.addCell(label);
					label = new Label(4, 1, "Qtd Aguardando");
					sheet.addCell(label);
					label = new Label(5, 1, "Total Entregas");
					sheet.addCell(label);
					for (Entregues e : listaEntregues) {

						label = new Label(0, r, e.getRa());
						sheet.addCell(label);
						label = new Label(1, r, e.getNome());
						sheet.addCell(label);
						label = new Label(2, r,
								String.valueOf(e.getAprovados()));
						sheet.addCell(label);
						label = new Label(3, r, String.valueOf(e.getCorrigir()));
						sheet.addCell(label);
						label = new Label(4, r, String.valueOf(e
								.getAguardando()));
						sheet.addCell(label);
						label = new Label(5, r, String.valueOf(e
								.getAguardando()
								+ e.getAprovados()
								+ e.getCorrigir()));
						sheet.addCell(label);

						r++;
					}

				} catch (SQLException | WriteException e) {
					e.printStackTrace();
				}

			}
		}
		try {
			workbook.write();
			workbook.close();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		if (!arq.exists()) {
			throw new ServletException("Erro no arquivo");
		}

		InputStream fis = new FileInputStream(arq);
		String mimeType = context.getMimeType(arq.getAbsolutePath());
		response.setContentType(mimeType != null ? mimeType
				: "application/octet-stream");
		response.setContentLength((int) arq.length());
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ arq.getName() + "\"");

		ServletOutputStream os = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read = 0;
		while ((read = fis.read(bufferData)) != -1) {
			os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
	}
}
