package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Course;
import model.Student;
import model.Studied;
import model.Studying;
import utilities.ErrorHandler;
import utilities.UtilView;

public class view extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 5054166201282114423L;
	private Controller controller = new Controller();
	private ErrorHandler errorHandler = new ErrorHandler();
	private ArrayList<JTextField> studPanelFields = new ArrayList<JTextField>();
	private ArrayList<JTextField> regGradePanelFields = new ArrayList<JTextField>();
	private ArrayList<JTextField> coursePanelFields = new ArrayList<JTextField>();
	private JPanel contentPane;
	private final int BUTTON_WIDTH = 108;
	private final int BUTTON_HEIGHT = 23;
	private final int TEXTFIELD_WIDTH = 159;
	private final int TEXTFIELD_HEIGHT = 25;
	private final int LABEL_WIDTH = 108;
	private final int LABEL_HEIGHT = 23;
	private final int LABEL_HEADLINE_WIDTH = 150;
	private final int LABEL_HEADLINE_HEIGHT = 23;
	private final int COMBOBOX_WIDHT = 85;
	private final int COMBOBOX_HEIGHT = 25;
	private JTextField textField_stud_pnr;
	private JTextField textField_regStud_pnr;
	private JTextField textField_regStud_name;
	private JTextField textField_regGrade_name;
	private JTextField textField_regGrade_pnr;
	private JTextField textField_course_courseCode;
	private JTextField textField_course_cname;
	private JTextField textField_course_points;
	private JTextField textField_searchInfo_courseCode;
	private JTextField textField_searchInfo_pnr;
	private JTextField textField_searchInfo_grade;
	private JTable table_stud_reg;
	private JTable table_stud_courses;
	private JTable table_regGrade;
	private JTable table_searchInfo;
	private JTable table_caccess;
	private JComboBox comboBox_regStud_ccode;
	private JComboBox comboBox_stud_course;
	private JTextField textField_stud_add_pnr;
	private JTextField textField_stud_add_name;
	private JTextField textField_stud_add_address;
	private JTextField textField_stud_delete_pnr;
	private JTextField textField_stud_delete_name;
	private JTextField textField_stud_delete_address;
	private DefaultTableModel stud_finished;
	private DefaultTableModel dtmCourse_results;
	private JTable table_stud_foundStud;
	private JTable table_stud_finished;
	private JTextField textField_course_delete_ccode;
	private JTextField textField_course_delete_cname;
	private JTextField textField_course_delete_points;
	private JTable table_course;
	private JTextField textField_course_search_ccode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					view frame = new view();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public view() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1262, 636);
		contentPane.add(tabbedPane);

		// ***********************************
		// ***********STUDENT TAB*************
		// ***********************************

		JPanel panel_student = new JPanel();
		tabbedPane.addTab("Student", panel_student);
		panel_student.setLayout(null);

		JLabel lbl_feedback = new JLabel("");
		lbl_feedback.setBounds(10, 640, 638, 20);
		contentPane.add(lbl_feedback);

		DefaultTableModel dtmStud_Finished = new DefaultTableModel();
		String[] finCourse = { "Course code", "Semester", "Grade" };
		dtmStud_Finished.setColumnIdentifiers(finCourse);

		DefaultTableModel dtmCourse_results = new DefaultTableModel();
		String[] resultsCourse = { "Personal Number", "Semester", "Grade" };
		dtmCourse_results.setColumnIdentifiers(resultsCourse);

		DefaultTableModel dtmStud_Current = new DefaultTableModel();
		String[] currentCourse = { "Course code", "Semester" };
		dtmStud_Current.setColumnIdentifiers(currentCourse);

		DefaultTableModel dtmStud_Search = new DefaultTableModel();
		String[] student = { "Personal number", "Name", "Address" };
		dtmStud_Search.setColumnIdentifiers(student);

		JComboBox<String> comboBox_stud_course = new JComboBox<String>();
		DefaultComboBoxModel<String> courselist = new DefaultComboBoxModel<String>();

		JButton btn_stud_search = new JButton("Search");
		btn_stud_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textField_stud_pnr.getText().trim().isEmpty()) {
					lbl_feedback.setText(errorHandler.noInput());
				} else {
					try {
						dtmStud_Search.setRowCount(0);
						dtmStud_Current.setRowCount(0);
						dtmStud_Finished.setRowCount(0);
						Student s = controller.getStudentAll(textField_stud_pnr.getText());
						if (s == null) {
							lbl_feedback.setText(errorHandler.noStudentFound(textField_stud_pnr.getText()));
						} else {
							String[] row = { s.getSpnr(), s.getSname(), s.getSaddress() };
							dtmStud_Search.addRow(row);
							if (s.getStudyingList().isEmpty()) {
								lbl_feedback.setText(errorHandler.noStudying(textField_stud_pnr.getText()));
							} else {
								for (Studying studying : s.getStudyingList()) {
									String[] currentCourses = { studying.getcCode(),
											studying.getSemester().toUpperCase() };
									dtmStud_Current.addRow(currentCourses);
								}
								if (s.getStudiedList().isEmpty()) {
									lbl_feedback.setText(errorHandler.noStudied(textField_stud_pnr.getText()));
								} else {
									for (Studied studied : s.getStudiedList()) {
										String[] finishedCourses = { studied.getcCode(),
												studied.getSemester().toUpperCase(), studied.getGrade().toUpperCase() };
										dtmStud_Finished.addRow(finishedCourses);
									}
								}
							}
							table_stud_foundStud.setModel(dtmStud_Search);
							table_stud_finished.setModel(dtmStud_Finished);
							table_stud_courses.setModel(dtmStud_Current);
						}
					} catch (Exception e) {
						lbl_feedback.setText("Error: " + errorHandler.handleException(e));
					}
				}
			}
		});

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(438, 11, 2, 582);
		panel_student.add(separator);
		btn_stud_search.setBounds(763, 47, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_search);

		JButton btn_stud_add_clear = new JButton("Clear");
		btn_stud_add_clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UtilView.clearAllFields(studPanelFields);
			}
		});
		btn_stud_add_clear.setBounds(63, 155, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_add_clear);

		JButton btn_stud_delete_delete = new JButton("Delete");
		btn_stud_delete_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField_stud_delete_pnr.getText().trim().isEmpty()) {
						lbl_feedback.setText(errorHandler.noInput());
					} else {
						controller.deleteStudent(textField_stud_delete_pnr.getText());
					}
				} catch (Exception e) {
				}
			}
		});
		btn_stud_delete_delete.setBounds(183, 449, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_delete_delete);

		JButton btn_stud_add_createNew = new JButton("Add new student");
		btn_stud_add_createNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField_stud_add_pnr.getText().trim().isEmpty()) {
						lbl_feedback.setText(errorHandler.noInput());
					} else {
						controller.createStudent(textField_stud_add_pnr.getText(), textField_stud_add_name.getText(),
								textField_stud_add_address.getText());
						lbl_feedback.setText("Student added!");
					}
				} catch (Exception e) {
				}
			}

		});
		btn_stud_add_createNew.setBounds(183, 155, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_add_createNew);

		JButton btnRegisterGrade = new JButton("Register Grade");
		btnRegisterGrade.setBounds(592, 537, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btnRegisterGrade);

		JButton btn_stud_delete_search = new JButton("Search");
		btn_stud_delete_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textField_stud_delete_pnr.getText().trim().isEmpty()) {
					lbl_feedback.setText(errorHandler.noInput());
				} else {
					try {
						Student s = controller.getStudent(textField_stud_delete_pnr.getText());
						if (s == null) {
							lbl_feedback.setText(errorHandler.noStudentFound(textField_stud_delete_pnr.getText()));
						} else {
							textField_stud_delete_name.setText(s.getSname());
							textField_stud_delete_address.setText(s.getSaddress());
						}
					} catch (Exception e) {
						lbl_feedback.setText("Error: " + errorHandler.handleException(e));
					}
				}
			}
		});

		btn_stud_delete_search.setBounds(303, 340, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_delete_search);

		JLabel lbl_stud_add_pnr = new JLabel("Personal number");
		lbl_stud_add_pnr.setBounds(12, 47, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_add_pnr);

		JLabel lbl_stud_add_name = new JLabel("Name:");
		lbl_stud_add_name.setBounds(12, 83, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_add_name);

		JLabel lbl_stud_add_address = new JLabel("Address:");
		lbl_stud_add_address.setBounds(12, 119, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_add_address);

		JLabel lbl_stud_addStudent = new JLabel("Add Student");
		lbl_stud_addStudent.setBounds(12, 11, LABEL_HEADLINE_WIDTH, LABEL_HEADLINE_HEIGHT);
		panel_student.add(lbl_stud_addStudent);

		JLabel lbl_stud_searchStudent = new JLabel("Search Student");
		lbl_stud_searchStudent.setBounds(472, 11, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_searchStudent);

		JLabel lbl_stud_regStudent = new JLabel("Register grade for selected course");
		lbl_stud_regStudent.setBounds(472, 508, 187, 23);
		panel_student.add(lbl_stud_regStudent);

		JLabel lbl_stud_deleteStudent = new JLabel("Delete student");
		lbl_stud_deleteStudent.setBounds(12, 300, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_deleteStudent);

		JLabel lbl_stud_delete_pnr = new JLabel("Persnal number:");
		lbl_stud_delete_pnr.setBounds(12, 340, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_delete_pnr);

		JLabel lbl_stud_delete_name = new JLabel("Name:");
		lbl_stud_delete_name.setBounds(12, 375, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_delete_name);

		JLabel lbl_stud_delete_address = new JLabel("Address:");
		lbl_stud_delete_address.setBounds(12, 412, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_delete_address);

		JLabel lbl_stud_pnr = new JLabel("Personal number:");
		lbl_stud_pnr.setBounds(472, 47, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_pnr);

		JLabel lbl_stud_search_courseCode = new JLabel("Course code: ");
		lbl_stud_search_courseCode.setBounds(472, 83, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_search_courseCode);

		textField_stud_pnr = new JTextField();
		textField_stud_pnr.setBounds(592, 46, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_pnr);
		textField_stud_pnr.setColumns(10);
		studPanelFields.add(textField_stud_pnr);

		textField_stud_add_pnr = new JTextField();
		textField_stud_add_pnr.setBounds(132, 46, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_add_pnr);
		textField_stud_add_pnr.setColumns(10);

		textField_stud_add_name = new JTextField();
		textField_stud_add_name.setBounds(132, 82, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_add_name);
		textField_stud_add_name.setColumns(10);

		textField_stud_add_address = new JTextField();
		textField_stud_add_address.setBounds(132, 118, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_add_address);
		textField_stud_add_address.setColumns(10);

		textField_stud_delete_pnr = new JTextField();
		textField_stud_delete_pnr.setBounds(132, 339, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_delete_pnr);
		textField_stud_delete_pnr.setColumns(10);

		textField_stud_delete_name = new JTextField();
		textField_stud_delete_name.setEditable(false);
		textField_stud_delete_name.setBounds(132, 374, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_delete_name);
		textField_stud_delete_name.setColumns(10);

		textField_stud_delete_address = new JTextField();
		textField_stud_delete_address.setEditable(false);
		textField_stud_delete_address.setBounds(132, 411, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_delete_address);
		textField_stud_delete_address.setColumns(10);

		JScrollPane scrollPane_stud_foundStudent = new JScrollPane();
		scrollPane_stud_foundStudent.setBounds(472, 121, 391, 88);
		table_stud_foundStud = new JTable();
		scrollPane_stud_foundStudent.setViewportView(table_stud_foundStud);
		panel_student.add(scrollPane_stud_foundStudent);

		JScrollPane scrollPane_stud_courses = new JScrollPane();
		scrollPane_stud_courses.setBounds(472, 278, 293, 219);
		table_stud_courses = new JTable();
		scrollPane_stud_courses.setViewportView(table_stud_courses);
		panel_student.add(scrollPane_stud_courses);

		JComboBox<String> comboBox_stud_grade = new JComboBox<String>();
		comboBox_stud_grade.setBounds(472, 536, 108, 25);
		panel_student.add(comboBox_stud_grade);
		comboBox_stud_grade.addItem("A");
		comboBox_stud_grade.addItem("B");
		comboBox_stud_grade.addItem("C");
		comboBox_stud_grade.addItem("D");
		comboBox_stud_grade.addItem("E");
		comboBox_stud_grade.addItem("U");

		comboBox_stud_course.setToolTipText("");
		comboBox_stud_course.setBounds(592, 82, COMBOBOX_WIDHT, COMBOBOX_HEIGHT);
		panel_student.add(comboBox_stud_course);

		JScrollPane scrollPane_stud_finished = new JScrollPane();
		scrollPane_stud_finished.setBounds(793, 278, 367, 219);
		panel_student.add(scrollPane_stud_finished);
		table_stud_finished = new JTable();
		scrollPane_stud_finished.setViewportView(table_stud_finished);

		JLabel lblFinishedCourses = new JLabel("Finished Courses:");
		lblFinishedCourses.setBounds(793, 249, 108, 14);
		panel_student.add(lblFinishedCourses);

		JLabel lblCurrentCourses = new JLabel("Current Courses:");
		lblCurrentCourses.setBounds(472, 249, 108, 14);
		panel_student.add(lblCurrentCourses);

		// ***********************************
		// *******REGISTER STUDENT TAB********
		// ***********************************
		JComboBox<String> comboBox_regStud_ccode = new JComboBox<String>();
		DefaultComboBoxModel<String> ccodesList = new DefaultComboBoxModel<String>();

		JPanel panel_regStudent = new JPanel();
		tabbedPane.addTab("Register student", panel_regStudent);
		panel_regStudent.setLayout(null);

		JButton btn_regStud_delete = new JButton("Delete");
		btn_regStud_delete.setBounds(393, 301, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_regStudent.add(btn_regStud_delete);

		JButton btn_regStud_register = new JButton("Register");
		btn_regStud_register.setBounds(513, 301, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_regStudent.add(btn_regStud_register);

		JButton btn_regStud_search = new JButton("Search");
		btn_regStud_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textField_regStud_pnr.getText().trim().isEmpty()) {
					lbl_feedback.setText(errorHandler.noInput());
				} else {
					try {
						Student s = controller.getStudent(textField_regStud_pnr.getText());
						ArrayList<String> c = controller.getCcodes();
						for (String string : c) {
							ccodesList.addElement(string);
						}
						comboBox_regStud_ccode.setModel(ccodesList);

						if (s == null) {
							lbl_feedback.setText(errorHandler.noStudentFound(textField_regStud_pnr.getText()));
						} else {
							textField_regStud_name.setText(s.getSname());
						}
					} catch (Exception e) {
						lbl_feedback.setText("Error: " + errorHandler.handleException(e));
					}
				}
			}
		});
		btn_regStud_search.setBounds(325, 27, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_regStudent.add(btn_regStud_search);

		JButton btn_regStud_add = new JButton("Add");
		btn_regStud_add.setBounds(15, 212, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_regStudent.add(btn_regStud_add);

		textField_regStud_pnr = new JTextField();
		textField_regStud_pnr.setColumns(10);
		textField_regStud_pnr.setBounds(154, 26, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_regStudent.add(textField_regStud_pnr);

		textField_regStud_name = new JTextField();
		textField_regStud_name.setEditable(false);
		textField_regStud_name.setBounds(154, 60, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_regStudent.add(textField_regStud_name);
		textField_regStud_name.setColumns(10);

		JLabel lbl_regStud_name = new JLabel("Name:");
		lbl_regStud_name.setBounds(15, 61, LABEL_WIDTH, LABEL_HEIGHT);
		panel_regStudent.add(lbl_regStud_name);

		JLabel lbl_regStud_pnr = new JLabel("Personal number:");
		lbl_regStud_pnr.setBounds(15, 26, LABEL_WIDTH, LABEL_HEIGHT);
		panel_regStudent.add(lbl_regStud_pnr);

		JLabel lbl_regStud_ccode = new JLabel("Course code:");
		lbl_regStud_ccode.setBounds(15, 95, LABEL_WIDTH, LABEL_HEIGHT);
		panel_regStudent.add(lbl_regStud_ccode);

		JLabel lbl_regStud_semester = new JLabel("Semester:");
		lbl_regStud_semester.setBounds(15, 156, LABEL_WIDTH, LABEL_HEIGHT);
		panel_regStudent.add(lbl_regStud_semester);

		comboBox_regStud_ccode.setMaximumRowCount(10);
		comboBox_regStud_ccode.setBounds(154, 98, COMBOBOX_WIDHT, COMBOBOX_HEIGHT);
		panel_regStudent.add(comboBox_regStud_ccode);

		JComboBox<String> comboBox_regStud_year = new JComboBox<String>();
		comboBox_regStud_year.setBounds(154, 155, COMBOBOX_WIDHT, COMBOBOX_HEIGHT);
		panel_regStudent.add(comboBox_regStud_year);

		comboBox_regStud_year.addItem("HT16");
		comboBox_regStud_year.addItem("VT16");
		comboBox_regStud_year.addItem("HT17");

		JScrollPane scrollPane_regStud = new JScrollPane();
		scrollPane_regStud.setBounds(15, 243, 366, 81);
		panel_regStudent.add(scrollPane_regStud);

		table_stud_reg = new JTable();
		scrollPane_regStud.setViewportView(table_stud_reg);

		// ***********************************
		// *********REGISTER GRADE TAB********
		// ***********************************

		JPanel panel_regGrade = new JPanel();
		tabbedPane.addTab("Register grade", panel_regGrade);
		panel_regGrade.setLayout(null);

		DefaultTableModel dtmcourses = new DefaultTableModel();
		String[] course = { "Code", "Semester" };
		dtmcourses.setColumnIdentifiers(course);

		JButton btn_regGrade_search = new JButton("Search");
		btn_regGrade_search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textField_regGrade_pnr.getText().trim().isEmpty()) {
					lbl_feedback.setText(errorHandler.noInput());
				} else {
					try {
						Student s = controller.getStudent(textField_regGrade_pnr.getText());
						ArrayList<Studying> studying = controller.getStudentStudying(textField_regGrade_pnr.getText());
						dtmcourses.setRowCount(0);

						if (s == null) {
							lbl_feedback.setText(errorHandler.noStudentFound(textField_regGrade_pnr.getText()));
							UtilView.clearNonSearchFields(regGradePanelFields);
						} else if (studying == null) {
							lbl_feedback.setText(errorHandler.noStudying(textField_regGrade_pnr.getText()));

						} else {
							textField_regGrade_name.setText(s.getSname());

							for (Studying stud : studying) {
								String[] studentsCourses = { stud.getcCode(), stud.getSemester().toUpperCase() };
								dtmcourses.addRow(studentsCourses);
							}

							table_regGrade.setModel(dtmcourses);
							lbl_feedback.setText(UtilView.studentFound(textField_regGrade_pnr.getText()));
						}
					} catch (Exception e) {
						lbl_feedback.setText(errorHandler.handleException(e));
					}
				}
			}
		});

		btn_regGrade_search.setBounds(290, 28, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_regGrade.add(btn_regGrade_search);

		JButton btn_regGrade_clear = new JButton("Clear");
		btn_regGrade_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dtmcourses.setRowCount(0);
				UtilView.clearAllFields(regGradePanelFields);
			}
		});
		btn_regGrade_clear.setBounds(10, 298, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_regGrade.add(btn_regGrade_clear);

		JButton btn_regGrade_save = new JButton("Save");
		btn_regGrade_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_regGrade_save.setBounds(280, 298, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_regGrade.add(btn_regGrade_save);

		textField_regGrade_pnr = new JTextField();
		textField_regGrade_pnr.setColumns(10);
		textField_regGrade_pnr.setBounds(119, 27, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_regGrade.add(textField_regGrade_pnr);
		regGradePanelFields.add(textField_regGrade_pnr);

		textField_regGrade_name = new JTextField();
		textField_regGrade_name.setEditable(false);
		textField_regGrade_name.setColumns(10);
		textField_regGrade_name.setBounds(119, 59, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_regGrade.add(textField_regGrade_name);
		regGradePanelFields.add(textField_regGrade_name);

		JLabel lbl_regGrade_pnr = new JLabel("Personal number:");
		lbl_regGrade_pnr.setBounds(10, 27, LABEL_WIDTH, LABEL_HEIGHT);
		panel_regGrade.add(lbl_regGrade_pnr);

		JLabel lbl_regGrade_name = new JLabel("Name:");
		lbl_regGrade_name.setBounds(10, 62, LABEL_WIDTH, LABEL_HEIGHT);
		panel_regGrade.add(lbl_regGrade_name);

		JLabel lbl_regGarde_grade = new JLabel("Grade:");
		lbl_regGarde_grade.setBounds(10, 241, LABEL_WIDTH, LABEL_HEIGHT);
		panel_regGrade.add(lbl_regGarde_grade);

		JComboBox<String> comboBox_regGrade_grade = new JComboBox<String>();
		comboBox_regGrade_grade.setMaximumRowCount(10);
		comboBox_regGrade_grade.setBounds(119, 241, COMBOBOX_WIDHT, COMBOBOX_HEIGHT);
		panel_regGrade.add(comboBox_regGrade_grade);

		comboBox_regGrade_grade.addItem("A");
		comboBox_regGrade_grade.addItem("B");
		comboBox_regGrade_grade.addItem("C");
		comboBox_regGrade_grade.addItem("D");
		comboBox_regGrade_grade.addItem("E");
		comboBox_regGrade_grade.addItem("U");
		comboBox_regGrade_grade.setSelectedIndex(0);

		JScrollPane scrollPane_regGrade = new JScrollPane();
		scrollPane_regGrade.setBounds(119, 106, 237, 109);
		panel_regGrade.add(scrollPane_regGrade);

		table_regGrade = new JTable();
		scrollPane_regGrade.setViewportView(table_regGrade);
		table_regGrade.setModel(dtmcourses);
		table_regGrade.setRowHeight(20);

		// ***********************************
		// ************COURSE TAB*************
		// ***********************************

		JPanel panel_course = new JPanel();
		tabbedPane.addTab("Course", panel_course);
		panel_course.setLayout(null);

		JButton btn_course_clear = new JButton("Clear");
		btn_course_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UtilView.clearAllFields(coursePanelFields);
			}
		});
		btn_course_clear.setBounds(69, 164, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_clear);

		JButton btn_course_delete = new JButton("Delete");
		btn_course_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField_course_delete_ccode.getText().trim().isEmpty()) {
						lbl_feedback.setText(errorHandler.noInput());
					} else {
						controller.deleteCourse(textField_course_delete_ccode.getText());
					}
				} catch (Exception e) {
				}
			}
		});
		btn_course_delete.setBounds(182, 503, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_delete);

		JButton btn_course_add = new JButton("Add Course");
		btn_course_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField_course_courseCode.getText().trim().isEmpty()) {
						lbl_feedback.setText(errorHandler.noInput());
					} else {
						controller.createCourse(textField_course_courseCode.getText(), textField_course_cname.getText(),
								textField_course_points.getText());
						lbl_feedback.setText("Course successfully added!");
					}
				} catch (Exception e) {
				}
			}

		});
		btn_course_add.setBounds(187, 164, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_add);

		textField_course_courseCode = new JTextField();
		textField_course_courseCode.setColumns(10);
		textField_course_courseCode.setBounds(131, 33, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_course.add(textField_course_courseCode);
		coursePanelFields.add(textField_course_courseCode);

		textField_course_cname = new JTextField();
		textField_course_cname.setColumns(10);
		textField_course_cname.setBounds(131, 79, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_course.add(textField_course_cname);
		coursePanelFields.add(textField_course_cname);

		textField_course_points = new JTextField();
		textField_course_points.setColumns(10);
		textField_course_points.setBounds(131, 128, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_course.add(textField_course_points);
		coursePanelFields.add(textField_course_points);

		JLabel lbl_course_ccode = new JLabel("Course code:");
		lbl_course_ccode.setBounds(10, 33, LABEL_WIDTH, LABEL_HEIGHT);
		panel_course.add(lbl_course_ccode);

		JLabel lbl_course_name = new JLabel("Course name:");
		lbl_course_name.setBounds(10, 79, LABEL_WIDTH, LABEL_HEIGHT);
		panel_course.add(lbl_course_name);

		JLabel lbl_course_points = new JLabel("Points:");
		lbl_course_points.setBounds(10, 128, LABEL_WIDTH, LABEL_HEIGHT);
		panel_course.add(lbl_course_points);

		JLabel lbl_course_addcourse = new JLabel("Add Course");
		lbl_course_addcourse.setBounds(10, 8, 80, 14);
		panel_course.add(lbl_course_addcourse);

		JLabel lbl_course_delete = new JLabel("Delete Course");
		lbl_course_delete.setBounds(10, 362, 108, 23);
		panel_course.add(lbl_course_delete);

		JLabel lbl_course_delete_ccode = new JLabel("Course Code");
		lbl_course_delete_ccode.setBounds(10, 396, 108, 23);
		panel_course.add(lbl_course_delete_ccode);

		JLabel lbl_course_delete_cname = new JLabel("Course Name:");
		lbl_course_delete_cname.setBounds(10, 431, 108, 23);
		panel_course.add(lbl_course_delete_cname);

		JLabel lbl_course_delete_cpoints = new JLabel("Points");
		lbl_course_delete_cpoints.setBounds(10, 468, 108, 23);
		panel_course.add(lbl_course_delete_cpoints);

		textField_course_delete_ccode = new JTextField();
		textField_course_delete_ccode.setColumns(10);
		textField_course_delete_ccode.setBounds(130, 395, 159, 25);
		panel_course.add(textField_course_delete_ccode);

		textField_course_delete_cname = new JTextField();
		textField_course_delete_cname.setEditable(false);
		textField_course_delete_cname.setColumns(10);
		textField_course_delete_cname.setBounds(130, 430, 159, 25);
		panel_course.add(textField_course_delete_cname);

		textField_course_delete_points = new JTextField();
		textField_course_delete_points.setEditable(false);
		textField_course_delete_points.setColumns(10);
		textField_course_delete_points.setBounds(130, 467, 159, 25);
		panel_course.add(textField_course_delete_points);

		JButton btn_course_delete_search = new JButton("Search");
		btn_course_delete_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textField_course_delete_ccode.getText().trim().isEmpty()) {
					lbl_feedback.setText(errorHandler.noInput());
				} else {
					try {
						Course c = controller.getCourse(textField_course_delete_ccode.getText());
						if (c == null) {
							lbl_feedback.setText(errorHandler.noCourseFound(textField_course_delete_ccode.getText()));
						} else {
							textField_course_delete_cname.setText(c.getCname());
							textField_course_delete_points.setText(Integer.toString(c.getCpoint()));
						}
					} catch (Exception e) {
						lbl_feedback.setText("Error: " + errorHandler.handleException(e));
					}
				}
			}
		});
		btn_course_delete_search.setBounds(301, 396, 108, 23);
		panel_course.add(btn_course_delete_search);

		JScrollPane scrollPane_course = new JScrollPane();
		scrollPane_course.setBounds(674, 247, 527, 309);
		panel_course.add(scrollPane_course);

		table_course = new JTable();
		scrollPane_course.setViewportView(table_course);

		textField_course_search_ccode = new JTextField();
		textField_course_search_ccode.setColumns(10);
		textField_course_search_ccode.setBounds(792, 33, 159, 25);
		panel_course.add(textField_course_search_ccode);

		JLabel lbl_course_search_gradeA = new JLabel("% of Students with grade A");
		lbl_course_search_gradeA.setBounds(674, 567, 167, 14);
		panel_course.add(lbl_course_search_gradeA);

		JLabel lbl_course_search_showgradeA = new JLabel("");
		lbl_course_search_showgradeA.setBounds(851, 567, 46, 14);
		panel_course.add(lbl_course_search_showgradeA);

		JLabel lbl_course_search_header = new JLabel("Enrolled Students");
		lbl_course_search_header.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_course_search_header.setBounds(674, 8, 189, 14);
		panel_course.add(lbl_course_search_header);

		JButton btn_course_search_showresult = new JButton("Show Result");
		btn_course_search_showresult.setBounds(1010, 213, 108, 23);
		panel_course.add(btn_course_search_showresult);

		JLabel lblSelectCourse = new JLabel("Select course:");
		lblSelectCourse.setBounds(674, 38, 115, 14);
		panel_course.add(lblSelectCourse);

		JLabel lbl_course_courseInfo = new JLabel("Course Info");
		lbl_course_courseInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_course_courseInfo.setBounds(674, 146, 128, 14);
		panel_course.add(lbl_course_courseInfo);

		JButton btn_course_searchEnrolled = new JButton("Show Result");
		btn_course_searchEnrolled.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (textField_course_search_ccode.getText().trim().isEmpty()) {
					lbl_feedback.setText(errorHandler.noInput());
				} else {
					try {
						ArrayList<Studied> r = controller.getCourseResult(textField_course_search_ccode.getText());
						dtmCourse_results.setRowCount(0);

						if (r == null) {
							lbl_feedback.setText(errorHandler.noStudentFound(textField_course_search_ccode.getText()));
							UtilView.clearNonSearchFields(regGradePanelFields);
						} else {

							for (Studied re : r) {
								String[] studentsCourses = { re.getsPnr(), re.getSemester().toUpperCase(),
										re.getGrade().toUpperCase() };
								dtmCourse_results.addRow(studentsCourses);
							}

							table_course.setModel(dtmCourse_results);
							lbl_feedback.setText(UtilView.studentFound(textField_regGrade_pnr.getText()));
							lbl_course_search_showgradeA
									.setText(controller.acedIt(textField_course_search_ccode.getText()));
						}
					} catch (Exception e) {
						lbl_feedback.setText(errorHandler.handleException(e));
					}
				}
			}
		});
		btn_course_searchEnrolled.setBounds(1010, 109, 108, 23);
		panel_course.add(btn_course_searchEnrolled);

		JRadioButton rdbtn_course_showAll = new JRadioButton("Show all courses");
		rdbtn_course_showAll.setBounds(674, 164, 109, 23);
		panel_course.add(rdbtn_course_showAll);

		JRadioButton rdbtn_course_highestThrough = new JRadioButton(
				"Only shows the course with the highest throughoutput", false);
		rdbtn_course_highestThrough.setBounds(674, 190, 320, 23);
		panel_course.add(rdbtn_course_highestThrough);

		JRadioButton rdbtn_course_showNotFinished = new JRadioButton(
				"Show only students who hasn't finished the course", false);
		rdbtn_course_showNotFinished.setBounds(674, 66, 301, 23);
		panel_course.add(rdbtn_course_showNotFinished);

		ButtonGroup btngr_course = new ButtonGroup();
		btngr_course.add(rdbtn_course_showAll);
		btngr_course.add(rdbtn_course_highestThrough);
		btngr_course.add(rdbtn_course_showNotFinished);

		// ***********************************
		// *********SEARCH INFO TAB***********
		// ***********************************

		JPanel panel_searchInfo = new JPanel();
		tabbedPane.addTab("Search information", panel_searchInfo);
		panel_searchInfo.setLayout(null);

		JButton btn_searchInfo_search = new JButton("Search");
		btn_searchInfo_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_searchInfo_search.setBounds(298, 73, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_searchInfo.add(btn_searchInfo_search);

		textField_searchInfo_courseCode = new JTextField();
		textField_searchInfo_courseCode.setColumns(10);
		textField_searchInfo_courseCode.setBounds(127, 72, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_searchInfo.add(textField_searchInfo_courseCode);

		textField_searchInfo_pnr = new JTextField();
		textField_searchInfo_pnr.setColumns(10);
		textField_searchInfo_pnr.setBounds(127, 120, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_searchInfo.add(textField_searchInfo_pnr);

		textField_searchInfo_grade = new JTextField();
		textField_searchInfo_grade.setColumns(10);
		textField_searchInfo_grade.setBounds(127, 166, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_searchInfo.add(textField_searchInfo_grade);

		JLabel lbl_searchInfo_selectOption = new JLabel("Select option:");
		lbl_searchInfo_selectOption.setBounds(10, 22, LABEL_WIDTH, LABEL_HEIGHT);
		panel_searchInfo.add(lbl_searchInfo_selectOption);

		JLabel lbl_searchInfo_courseCode = new JLabel("Course code:");
		lbl_searchInfo_courseCode.setBounds(10, 72, LABEL_WIDTH, LABEL_HEIGHT);
		panel_searchInfo.add(lbl_searchInfo_courseCode);

		JLabel lbl_searchInfo_personalNumber = new JLabel("Personal number:");
		lbl_searchInfo_personalNumber.setBounds(10, 120, LABEL_WIDTH, LABEL_HEIGHT);
		panel_searchInfo.add(lbl_searchInfo_personalNumber);

		JLabel lbl_searchInfo_grade = new JLabel("Grade:");
		lbl_searchInfo_grade.setBounds(10, 166, LABEL_WIDTH, LABEL_HEIGHT);
		panel_searchInfo.add(lbl_searchInfo_grade);

		JComboBox<String> comboBox_searchInfo_selectOption = new JComboBox<String>();
		comboBox_searchInfo_selectOption.setBounds(130, 22, COMBOBOX_WIDHT, COMBOBOX_HEIGHT);
		panel_searchInfo.add(comboBox_searchInfo_selectOption);

		JScrollPane scrollPane_searchInfo = new JScrollPane();
		scrollPane_searchInfo.setBounds(130, 212, 237, 109);
		panel_searchInfo.add(scrollPane_searchInfo);

		table_searchInfo = new JTable();
		scrollPane_searchInfo.setViewportView(table_searchInfo);

		// ***************************************
		// ***********CRONUS ACCESS TAB***********
		// ***************************************

		table_caccess = new JTable();
		JPanel panel_caccess = new JPanel();
		tabbedPane.addTab("Cronus Access", panel_caccess);
		panel_caccess.setLayout(null);

		JButton btn_caccess_go = new JButton("Go");
		btn_caccess_go.setBounds(216, 22, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_caccess.add(btn_caccess_go);

		JLabel lbl_caccess_selectOption = new JLabel("Select query:");
		lbl_caccess_selectOption.setBounds(10, 22, LABEL_WIDTH, LABEL_HEIGHT);
		panel_caccess.add(lbl_caccess_selectOption);

		String[] test = { "Choose...", "Query1", "Query2" };

		JComboBox<String> comboBox_caccess_selectOption = new JComboBox(test);
		comboBox_caccess_selectOption.setBounds(119, 21, COMBOBOX_WIDHT, COMBOBOX_HEIGHT);

		panel_caccess.add(comboBox_caccess_selectOption);

		JScrollPane scrollPane_caccess = new JScrollPane();
		scrollPane_caccess.setBounds(130, 212, 237, 109);
		panel_caccess.add(scrollPane_caccess);

		table_caccess = new JTable();
		scrollPane_caccess.setViewportView(table_caccess);

		JSeparator separator_caccess = new JSeparator();
		separator_caccess.setBounds(10, 352, 599, 7);
		panel_caccess.add(separator_caccess);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Cronus Database", null, panel, null);
		panel.setLayout(null);

		JLabel lblSelectOption = new JLabel("Select option:");
		lblSelectOption.setBounds(44, 95, 112, 39);
		panel.add(lblSelectOption);

		JLabel lblSelectQuery = new JLabel("Select query:");
		lblSelectQuery.setBounds(44, 150, 112, 39);
		panel.add(lblSelectQuery);

		String[] microsoft = { "Excel", "Access" };

		JComboBox comboBox_word_excel = new JComboBox(microsoft);
		comboBox_word_excel.setBounds(171, 101, 144, 26);
		panel.add(comboBox_word_excel);

		String[] query = { "", "1", "2", "3", "4", "5", "6", "7" };

		JComboBox comboBox_cdatabase_query = new JComboBox(query);
		comboBox_cdatabase_query.setBounds(171, 156, 144, 26);
		panel.add(comboBox_cdatabase_query);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(30, 297, 900, 2);
		panel.add(separator_1);

		JLabel lblSqlQuery = new JLabel("SQL query");
		lblSqlQuery.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSqlQuery.setBounds(383, 30, 112, 31);
		panel.add(lblSqlQuery);

		JLabel lblOpen = new JLabel("Open form");
		lblOpen.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOpen.setBounds(383, 330, 112, 31);
		panel.add(lblOpen);

		JLabel lbl_oform_selectOption = new JLabel("Select option:");
		lbl_oform_selectOption.setBounds(30, 406, 112, 39);
		panel.add(lbl_oform_selectOption);

		String[] micoffice = { "Access", "Excel", "Word" };

		JComboBox comboBox_oform_selectOption = new JComboBox(micoffice);
		comboBox_oform_selectOption.setBounds(171, 412, 144, 26);
		panel.add(comboBox_oform_selectOption);

		JLabel lbl_oform_select = new JLabel("Select :");
		lbl_oform_select.setBounds(30, 466, 112, 39);
		panel.add(lbl_oform_select);

		String[] ec = { "Employee", "Customer" };

		JComboBox comboBox_oform_select = new JComboBox(ec);
		comboBox_oform_select.setBounds(171, 472, 144, 26);
		panel.add(comboBox_oform_select);

		JButton btnGo = new JButton("GO");
		btnGo.setBounds(40, 224, 102, 29);
		panel.add(btnGo);

		JButton button_1 = new JButton("GO");
		button_1.setBounds(30, 546, 102, 29);
		panel.add(button_1);

	}
}
