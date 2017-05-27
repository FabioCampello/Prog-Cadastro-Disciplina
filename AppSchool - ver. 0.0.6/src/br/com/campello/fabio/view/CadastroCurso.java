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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.campello.fabio.controller.ConexaoMySQL;
import br.com.campello.fabio.model.Curso;

public class CadastroCurso extends JFrame {

	// *************************************************************************************************************************************
	// DECLARAÇÃO DE COMPONENTES DA TELA
	// *************************************************************************************************************************************
	private JPanel contentPane;
	private JTextField jtfNomeCurso;
	private JTable table;
	private JComboBox jcbPeriodo;
	DefaultTableModel modelo = new DefaultTableModel();

	// *************************************************************************************************************************************
	// MÉTODO PRINCIPAL MAIN
	// *************************************************************************************************************************************
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					CadastroCurso frame = new CadastroCurso();
					frame.setVisible(true);
				} catch (Exception e) {

					String msg = "Erro! Tipo " + e.getMessage();
					System.out.println(msg);
				}
			}

		});
	}

	// *************************************************************************************************************************************
	// CONSTRUTOR DA JANELA
	// *************************************************************************************************************************************
	public CadastroCurso() {
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

		JLabel lblDisciplinas = new JLabel("Cursos");
		lblDisciplinas.setForeground(Color.WHITE);
		lblDisciplinas.setFont(new Font("Trebuchet MS", Font.BOLD, 28));
		lblDisciplinas.setBounds(218, 11, 150, 32);
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

		JLabel label = new JLabel("Curso");
		label.setBounds(10, 14, 46, 14);
		panelCampos.add(label);

		jtfNomeCurso = new JTextField();
		jtfNomeCurso.setColumns(10);
		jtfNomeCurso.setBounds(50, 11, 259, 20);
		panelCampos.add(jtfNomeCurso);

		JLabel label_1 = new JLabel("Per\u00EDodo");
		label_1.setBounds(319, 14, 46, 14);
		panelCampos.add(label_1);

		jcbPeriodo = new JComboBox();
		jcbPeriodo.setModel(new DefaultComboBoxModel(new String[] { "", "Manh\u00E3", "Tarde", "Noite" }));
		jcbPeriodo.setBounds(366, 11, 148, 20);
		panelCampos.add(jcbPeriodo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 42, 504, 108);
		panelCampos.add(scrollPane);

		table = new JTable(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "idCurso", "nomeCurso", "periodo" }));
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "C\u00F3d", "nomeCurso", "periodo" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(15);
		scrollPane.setViewportView(table);

		// ******************************************************************************************************************************
		// EVENTO DO BOTÃO CADASTRAR
		// ******************************************************************************************************************************
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Curso c = new Curso();

				if (jtfNomeCurso.getText().isEmpty()) {

					String msg = "O campo nome do curso deve ser preenchido!";
					JOptionPane.showMessageDialog(rootPane, msg, "ERRO DE UTILIZAÇÂO", JOptionPane.ERROR_MESSAGE);
					jcbPeriodo.setSelectedItem("");
				} else {
					c.setNomeCurso(jtfNomeCurso.getText());
					c.setPeriodo((String) jcbPeriodo.getSelectedItem());

					if (c.getPeriodo().isEmpty()) {
						String msg = "Informe um período!!";
						JOptionPane.showMessageDialog(rootPane, msg, "ERRO DE UTILIZAÇÂO", JOptionPane.ERROR_MESSAGE);
						jtfNomeCurso.setText("");
					} else {

						String cmd = "insert into Curso(nomeCurso, periodo) values(' " + c.getNomeCurso() + "', '"
								+ c.getPeriodo() + "')";

						System.out.println(cmd);

						try {
							Connection con = ConexaoMySQL.getInstance().getConnection();
							con.createStatement().executeUpdate(cmd);

							String msg = "Curso cadastrado com sucesso!!!";
							JOptionPane.showMessageDialog(rootPane, msg, "DADOS SALVOS!",
									JOptionPane.INFORMATION_MESSAGE);

						} catch (SQLException e1) {

							String msg2 = "Erro! Tipo " + e1.getMessage();
							JOptionPane.showMessageDialog(null, msg2, "ERRO DE UTILIZAÇÂO", JOptionPane.ERROR_MESSAGE);

						}

						new CadastroCurso().setVisible(true);
						dispose();
					}
				}

			}
		});
		btnCadastrar.setBounds(177, 291, 100, 25);
		contentPane.add(btnCadastrar);

		// ******************************************************************************************************************************
		// EVENTO DO BOTÃO EXCLUIR
		// ******************************************************************************************************************************
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(287, 291, 100, 25);
		contentPane.add(btnExcluir);

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
		btnExcluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new ExcluirCurso().setVisible(true);
				dispose();

			}

		});
		table.editCellAt(0, TEXT_CURSOR);

		loadTable();

	}

	// ******************************************************************************************************************************
	// MÉTODO DE COSNSTRUÇÃO DA TABELA COM OS RESULTADOS DO BANCO
	// ******************************************************************************************************************************
	private void loadTable() {

		try {
			Connection con = ConexaoMySQL.getInstance().getConnection();
			String cmd = "select * from Curso";
			ResultSet rs = con.createStatement().executeQuery(cmd);

			System.out.println(cmd);
			System.out.println(rs);

			while (rs.next()) {
				int idCursoDB = rs.getInt("idCurso");
				String nomeCursoDB = rs.getString("nomeCurso");
				String periodoDB = rs.getString("periodo");

				Vector v = new Vector();
				v.addElement(idCursoDB);
				v.addElement(nomeCursoDB);
				v.addElement(periodoDB);

				((DefaultTableModel) table.getModel()).addRow(v);
			}

		} catch (SQLException e) {

			String msg = "Erro! Tipo " + e.getMessage();
			System.out.println(msg);
		}

	}
}
