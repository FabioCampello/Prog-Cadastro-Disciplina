//******************************************************
//Autor...........: Fábio Francisco Campêlo
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
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.campello.fabio.controller.ConexaoMySQL;
import br.com.campello.fabio.model.Curso;

public class ExcluirDisciplina extends JFrame {

	// *************************************************************************************************************************************
	// DECLARAÇÃO DE COMPONENTES DA TELA
	// *************************************************************************************************************************************
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();
	static private ResultSet rs;
	static private String cmdcb;
	static private String nomeDB;
	static private JComboBox jcbNomeDisc;
	static private Statement st;

	// *************************************************************************************************************************************
	// MÉTODO PRINCIPAL MAIN
	// *************************************************************************************************************************************
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					ExcluirDisciplina frame = new ExcluirDisciplina();
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
	public ExcluirDisciplina() {
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

		// ******************************************************************************************************************************
		// EVENTO DO BOTÃO DISCIPLINAS
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
		// EVENTO DO BOTÃO CURSOS
		// ******************************************************************************************************************************
		JButton btnCursos = new JButton("Cursos");
		btnCursos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				new CadastroCurso().setVisible(true);
				dispose();

			}

		});
		btnCursos.setBounds(276, 157, 100, 25);
		panelCampos.add(btnCursos);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 42, 504, 108);
		panelCampos.add(scrollPane);

		// ******************************************************************************************************************************
		// CRIAÇÃO DA TABELA QUE EXIBE AS CURSOS
		// ******************************************************************************************************************************
		table = new JTable(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "idCurso", "nomeCurso", "periodo" }));
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3d", "Disciplina", "C. Hor\u00E1ria", "Vagas", "Curso" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(140);
		scrollPane.setViewportView(table);

		jcbNomeDisc = new JComboBox();
		jcbNomeDisc.setModel(new DefaultComboBoxModel(new String[] { "Informe a disciplina" }));
		jcbNomeDisc.setBounds(10, 11, 504, 20);
		panelCampos.add(jcbNomeDisc);

		// ******************************************************************************************************************************
		// EVENTO DO BOTÃO SOBRE
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

		// ******************************************************************************************************************************
		// EVENTO DO BOTÃO SAIR
		// ******************************************************************************************************************************
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(232, 291, 100, 25);
		contentPane.add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Connection con = ConexaoMySQL.getInstance().getConnection();
					String cmd2 = "delete from Disciplina where nome = '" + jcbNomeDisc.getSelectedItem() + "'";

					st = con.createStatement();
					int numLinhas = st.executeUpdate(cmd2);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				jcbNomeDisc.getSelectedItem();

				new ExcluirDisciplina().setVisible(true);
				dispose();

			}
		});
		table.editCellAt(0, TEXT_CURSOR);

		loadTable();
		loadCombo();

	}

	// ******************************************************************************************************************************
	// MÉTODO DE COSNSTRUÇÃO DA TABELA COM OS RESULTADOS DO BANCO
	// ******************************************************************************************************************************
	private void loadTable() {

		try {
			Connection con = ConexaoMySQL.getInstance().getConnection();
			// String cmd = "select * from Disciplina";
			String cmd = "select d.idDisciplina, d.nome, d.cargaHoraria, d.vagas, c.nomeCurso from disciplina as d join curso as c on c.idCurso = d.Curso_idCurso;";

			ResultSet rs = con.createStatement().executeQuery(cmd);

			System.out.println(cmd);
			System.out.println(rs);

			while (rs.next()) {
				int idDisciplinaDB = rs.getInt("idDisciplina");
				String nomeDB = rs.getString("nome");
				String cargaHorariaDB = rs.getString("cargaHoraria");
				String vagasDB = rs.getString("vagas");
				String Curso_idCursoDB = rs.getString("nomeCurso");

				Vector v = new Vector();
				v.addElement(idDisciplinaDB);
				v.addElement(nomeDB);
				v.addElement(cargaHorariaDB);
				v.addElement(vagasDB);
				v.addElement(Curso_idCursoDB);

				((DefaultTableModel) table.getModel()).addRow(v);
			}

		} catch (SQLException e) {
			String msg = "Erro! Tpipo " + e.getMessage();
			JOptionPane.showMessageDialog(null, msg, "ERRO DE UTILIZAÇÂO", JOptionPane.ERROR_MESSAGE);
		}

	}

	// ******************************************************************************************************************************
	// MÉTODO QUE INSERE OS NOMES DOS CURSOS NO JCOMBOBOX NOME CURSO
	// ******************************************************************************************************************************
	static private void loadCombo() {

		try {
			Connection con = ConexaoMySQL.getInstance().getConnection();
			cmdcb = "select nome from Disciplina";

			rs = con.createStatement().executeQuery(cmdcb);

			// AQUI É PARA EXEIBIR ALGUMA MENSAGEM DE ERRO PARA O DESENVOLVEDOR,
			// SE HOUVER
			System.out.println(cmdcb);
			System.out.println(rs);

			while (rs.next()) {

				nomeDB = rs.getString("nome");

				// ESTÁ OPÇÃO - MOSTRA NO COMBOBOX CURSO SOMENTE O NOME DO CURSO
				((DefaultComboBoxModel) jcbNomeDisc.getModel()).addElement(nomeDB);

			}

		} catch (SQLException e) {
			String msg = "Erro! Tpipo " + e.getMessage();
			JOptionPane.showMessageDialog(null, msg, "ERRO DE UTILIZAÇÂO", JOptionPane.ERROR_MESSAGE);
		}
	}
}
