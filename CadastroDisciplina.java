//******************************************************
//Autor...........: F�bio Francisco Camp�lo
//******************************************************

package br.com.campello.fabio.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.com.campello.fabio.controller.ConexaoMySQL;
import br.com.campello.fabio.model.Curso;
import br.com.campello.fabio.model.Disciplina;

public class CadastroDisciplina extends JFrame {

	// *************************************************************************************************************************************
	// DECLARA��O DE COMPONENTES DA TELA
	// *************************************************************************************************************************************
	private JPanel contentPane;
	private JTextField jtfNomeDisciplina;
	private JTextField jtfCargaHorariaDisciplina;
	private JTextField jtfVagasDisciplina;
	private static JComboBox jcbNomeCurso;
	static private ResultSet rs;
	static private String cmdcb;
	static private String idCursoDB;
	static private String nomeDB;
	static private String id;

	// *************************************************************************************************************************************
	// M�TODO PRINCIPAL MAIN
	// *************************************************************************************************************************************
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDisciplina frame = new CadastroDisciplina();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// *************************************************************************************************************************************
	// CONSTRUTOR DA JANELA
	// *************************************************************************************************************************************
	public CadastroDisciplina() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 360);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.BLUE);
		panelTitulo.setBounds(10, 11, 524, 65);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);

		JLabel lblDisciplinas = new JLabel("Disciplinas");
		lblDisciplinas.setForeground(Color.WHITE);
		lblDisciplinas.setFont(new Font("Trebuchet MS", Font.BOLD, 28));
		lblDisciplinas.setBounds(200, 11, 150, 32);
		panelTitulo.add(lblDisciplinas);

		JPanel panelCampos = new JPanel();
		panelCampos.setBorder(UIManager.getBorder("FormattedTextField.border"));
		panelCampos.setBounds(10, 87, 524, 193);
		contentPane.add(panelCampos);
		panelCampos.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 74, 46, 14);
		panelCampos.add(lblNome);

		jtfNomeDisciplina = new JTextField();
		jtfNomeDisciplina.setBounds(50, 71, 464, 20);
		panelCampos.add(jtfNomeDisciplina);
		jtfNomeDisciplina.setColumns(10);

		JLabel lblCargaHorria = new JLabel("Carga Hor\u00E1ria");
		lblCargaHorria.setBounds(82, 102, 93, 14);
		panelCampos.add(lblCargaHorria);

		jtfCargaHorariaDisciplina = new JTextField();
		jtfCargaHorariaDisciplina.setBounds(168, 99, 86, 20);
		panelCampos.add(jtfCargaHorariaDisciplina);
		jtfCargaHorariaDisciplina.setColumns(10);

		JLabel lblHoras = new JLabel("horas");
		lblHoras.setBounds(264, 102, 46, 14);
		panelCampos.add(lblHoras);

		JLabel lblNmeroDeVagas = new JLabel("Vagas");
		lblNmeroDeVagas.setBounds(313, 102, 40, 14);
		panelCampos.add(lblNmeroDeVagas);

		jtfVagasDisciplina = new JTextField();
		jtfVagasDisciplina.setColumns(10);
		jtfVagasDisciplina.setBounds(351, 99, 86, 20);
		panelCampos.add(jtfVagasDisciplina);

		// ******************************************************************************************************************************
		// EVENTO DO BOT�O CADASTRAR DISCIPLINAS
		// ******************************************************************************************************************************
		JButton btnDisciplinas = new JButton("Disciplinas");
		btnDisciplinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				new CadastroDisciplina().setVisible(true);
				dispose();

			}
		});
		btnDisciplinas.setBounds(166, 157, 100, 25);
		panelCampos.add(btnDisciplinas);

		// ******************************************************************************************************************************
		// EVENTO DO BOT�O CURSOS
		// ******************************************************************************************************************************
		JButton btnCursos = new JButton("Cursos");
		btnCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastroCurso().setVisible(true);
				dispose();
			}
		});
		btnCursos.setBounds(276, 157, 100, 25);
		panelCampos.add(btnCursos);

		// ******************************************************************************************************************************
		// EVENTO DO JCOMBOBOX NOMECURSO
		// ******************************************************************************************************************************
		jcbNomeCurso = new JComboBox();
		jcbNomeCurso.setModel(new DefaultComboBoxModel(new String[] { "Informe o nome do curso" }));
		jcbNomeCurso.setBounds(10, 37, 504, 20);
		panelCampos.add(jcbNomeCurso);

		// ******************************************************************************************************************************
		// EVENTO DO BOT�O CADASTRAR
		// ******************************************************************************************************************************
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Disciplina d = new Disciplina();
				Curso c = new Curso();
				d.setCurso(c);

				// ******************************************************************************************************************************
				// ESTRUTURA CONDICIONAL QUE TRATA DO PREENCHIMENTO DAS
				// INFORMA��ES DA DISCIPLINA E DE SUA INSER��O NO BANCO
				// ******************************************************************************************************************************
				if (jtfNomeDisciplina.getText().isEmpty() || jtfCargaHorariaDisciplina.getText().isEmpty()
						|| jtfVagasDisciplina.getText().isEmpty()) {
					String msg = "Todos os campos devem ser preenchidos!";
					JOptionPane.showMessageDialog(rootPane, msg, "ERRO DE UTILIZA��O", JOptionPane.ERROR_MESSAGE);
				} else if (jcbNomeCurso.getSelectedItem() == "Informe o nome do curso") {

					String msg = "Informe um curso!";
					JOptionPane.showMessageDialog(rootPane, msg, "ERRO DE UTILIZA��O", JOptionPane.ERROR_MESSAGE);

				} else {

					try {

						// ***************************************************************************************************************
						// INST�NCIA A CONEX�O COM O BANCO DE DADOS
						// ***************************************************************************************************************
						Connection con = ConexaoMySQL.getInstance().getConnection();

						// ***************************************************************************************************************
						// CAPTURA OS DADOS DOS CAMPOS DE TEXTO DA TELA CADASTRO
						// DISCIPLINA
						// ***************************************************************************************************************
						d.setNome(jtfNomeDisciplina.getText());
						d.setCargaHoraria(Integer.parseInt(jtfCargaHorariaDisciplina.getText()));
						d.setVagas(Integer.parseInt(jtfVagasDisciplina.getText()));

						if (jcbNomeCurso.getSelectedItem() != "Informe o nome do curso") {
							id = idCursoDB;
						}

						String cmd2 = "INSERT INTO Disciplina(nome, cargaHoraria, vagas, Curso_idCurso) VALUES ('"
								+ d.getNome() + "', '" + d.getCargaHoraria() + "', '" + d.getVagas() + "', '" + id
								+ "')";

						con.createStatement().executeUpdate(cmd2);

						String msg = "Disciplina cadastrada com sucesso!!!";
						JOptionPane.showMessageDialog(rootPane, msg, "DADOS SALVOS!", JOptionPane.INFORMATION_MESSAGE);
						jtfNomeDisciplina.setText("");
						jtfCargaHorariaDisciplina.setText("");
						jtfVagasDisciplina.setText("");
						jcbNomeCurso.setSelectedItem("Informe o nome do curso");

					} catch (SQLException e1) {

						e1.printStackTrace();
						String msg = "Informe o curso � que esta disciplina pertence!";
						JOptionPane.showMessageDialog(null, msg, "ERRO DE UTILIZA��O", JOptionPane.ERROR_MESSAGE);
						// jtfNomeDisciplina.setText("");
						// jtfCargaHorariaDisciplina.setText("");
						// jtfVagasDisciplina.setText("");
					} catch (NumberFormatException nfe) {

						String msg = "iplina pertence!";
						JOptionPane.showMessageDialog(null, msg, "ERRO DE UTILIZA��O", JOptionPane.ERROR_MESSAGE);
						jtfNomeDisciplina.setText("");
						jtfCargaHorariaDisciplina.setText("");
						jtfVagasDisciplina.setText("");
					}

				}
			}
		});
		btnCadastrar.setBounds(177, 291, 100, 25);
		contentPane.add(btnCadastrar);

		// ******************************************************************************************************************************
		// EVENTO DO BOT�O EXCLUIR
		// ******************************************************************************************************************************
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(287, 291, 100, 25);
		contentPane.add(btnExcluir);

		// ******************************************************************************************************************************
		// EVENTO DO BOT�O SOBRE
		// ******************************************************************************************************************************
		JButton btnSobre = new JButton("Sobre");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				new Development().setVisible(true);
				dispose();

			}
		});
		btnSobre.setBounds(465, 291, 70, 25);
		contentPane.add(btnSobre);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new ExcluirDisciplina().setVisible(true);
				dispose();
			}
		});

		loadCombo();

	}

	// ******************************************************************************************************************************
	// M�TODO QUE INSERE OS NOMES DOS CURSOS NO JCOMBOBOX NOME CURSO
	// ******************************************************************************************************************************
	static private void loadCombo() {

		try {
			Connection con = ConexaoMySQL.getInstance().getConnection();
			cmdcb = "select idCurso, nomeCurso from Curso";

			rs = con.createStatement().executeQuery(cmdcb);

			// AQUI � PARA EXEIBIR ALGUMA MENSAGEM DE ERRO PARA O DESENVOLVEDOR,
			// SE HOUVER
			System.out.println(cmdcb);
			System.out.println(rs);

			while (rs.next()) {

				idCursoDB = rs.getString("idCurso");
				nomeDB = rs.getString("nomeCurso");

				// EST� OP��O - MOSTRA NO COMBOBOX CURSO SOMENTE O NOME DO CURSO
				((DefaultComboBoxModel) jcbNomeCurso.getModel()).addElement(nomeDB);

			}

		} catch (SQLException e) {
			String msg = "Erro! Tpipo " + e.getMessage();
			JOptionPane.showMessageDialog(null, msg, "ERRO DE UTILIZA��O", JOptionPane.ERROR_MESSAGE);
		}
	}
}
