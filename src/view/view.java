package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
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
import controller.ControllerCronus;
import model.Course;
import model.Student;
import model.Studied;
import model.Studying;
import utilities.ExceptionHandler;
import utilities.FeedbackHandler;
import utilities.UtilView;

public class view extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 5054166201282114423L;
	private Controller controller = new Controller();
	private ControllerCronus controllerCronus = new ControllerCronus();
	private FeedbackHandler feedbackHandler = new FeedbackHandler();
	private ExceptionHandler exceptionHandler = new ExceptionHandler();
	private ArrayList<JTextField> studPanelFields = new ArrayList<JTextField>();
	private ArrayList<JTextField> regGradePanelFields = new ArrayList<JTextField>();
	private ArrayList<JTextField> coursePanelFields = new ArrayList<JTextField>();
	JLabel lbl_feedback = new JLabel("");
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
	private JTextField textField_course_courseCode;
	private JTextField textField_course_cname;
	private JTextField textField_course_points;
	private JTable table_stud_courses;
	private JTable table_caccess;
	private JComboBox comboBox_regStud_ccode;
	private JComboBox comboBox_stud_course;
	private JComboBox comboBox_stud_grade;
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
	private JLabel lbl_stud_showRemoveCourse;

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

		lbl_feedback.setBounds(10, 640, 638, 20);
		contentPane.add(lbl_feedback);

		JComboBox<String> comboBox_stud_grade = new JComboBox<String>();

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
					communicateMessage(feedbackHandler.noInput());
				} else {
					try {
						dtmStud_Search.setRowCount(0);
						dtmStud_Current.setRowCount(0);
						dtmStud_Finished.setRowCount(0);
						Student s = controller.getStudentAll(textField_stud_pnr.getText());
						if (s == null) {
							communicateMessage(feedbackHandler.noStudentFound(textField_stud_pnr.getText()));
						} else {
							String[] row = { s.getSpnr(), s.getSname(), s.getSaddress() };
							dtmStud_Search.addRow(row);
							if (s.getStudyingList().isEmpty()) {
								communicateMessage(feedbackHandler.noStudying(textField_stud_pnr.getText()));
							} else {
								for (Studying studying : s.getStudyingList()) {
									String[] currentCourses = { studying.getcCode(),
											studying.getSemester().toUpperCase() };
									dtmStud_Current.addRow(currentCourses);
								}
							}
							if (s.getStudiedList().isEmpty()) {
								communicateMessage(feedbackHandler.noStudied(textField_stud_pnr.getText()));
							} else {
								for (Studied studied : s.getStudiedList()) {
									String[] finishedCourses = { studied.getcCode(),
											studied.getSemester().toUpperCase(), studied.getGrade().toUpperCase() };
									dtmStud_Finished.addRow(finishedCourses);
								}
							}

							table_stud_foundStud.setModel(dtmStud_Search);
							table_stud_finished.setModel(dtmStud_Finished);
							table_stud_courses.setModel(dtmStud_Current);
						}
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
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
						communicateMessage(feedbackHandler.noInput());
					} else {
						controller.deleteStudent(textField_stud_delete_pnr.getText());
					}
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
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
						communicateMessage(feedbackHandler.noInput());
					} else {
						controller.createStudent(textField_stud_add_pnr.getText(), textField_stud_add_name.getText(),
								textField_stud_add_address.getText());
						lbl_feedback.setText("Student added!");
					}
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
				}
			}

		});
		btn_stud_add_createNew.setBounds(183, 155, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_add_createNew);

		JButton btnRegisterGrade = new JButton("Register Grade");
		btnRegisterGrade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField_stud_pnr.getText().trim().isEmpty()) {
						lbl_feedback.setText(feedbackHandler.noInput());
					} else {

						String grade = comboBox_stud_grade.getSelectedItem().toString();
						int st = table_stud_courses.getSelectedRow();
						String semester = dtmStud_Current.getValueAt(st, 1).toString();
						String ccode = dtmStud_Current.getValueAt(st, 0).toString();
						String spnr = textField_stud_pnr.getText();
						controller.registerGrade(semester, spnr, ccode, grade);
						controller.deleteStudying(spnr, ccode);
						lbl_feedback.setText("Grade registered!");
					}
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
				}
			}

		});
		btnRegisterGrade.setBounds(592, 512, 139, 23);
		panel_student.add(btnRegisterGrade);

		JButton btn_stud_delete_search = new JButton("Search");
		btn_stud_delete_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textField_stud_delete_pnr.getText().trim().isEmpty()) {
					communicateMessage(feedbackHandler.noInput());
				} else {
					try {
						Student s = controller.getStudent(textField_stud_delete_pnr.getText());
						if (s == null) {
							communicateMessage(feedbackHandler.noStudentFound(textField_stud_delete_pnr.getText()));
						} else {
							textField_stud_delete_name.setText(s.getSname());
							textField_stud_delete_address.setText(s.getSaddress());
						}
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
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
		lbl_stud_regStudent.setBounds(472, 483, 248, 23);
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

		JLabel lbl_stud_showRemoveCourse = new JLabel("");
		lbl_stud_showRemoveCourse.setBounds(693, 558, 58, 14);
		panel_student.add(lbl_stud_showRemoveCourse);

		JScrollPane scrollPane_stud_courses = new JScrollPane();
		scrollPane_stud_courses.setBounds(472, 253, 358, 219);
		table_stud_courses = new JTable();
		table_stud_courses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int sel = table_stud_courses.getSelectedRow();
					String ccode = dtmStud_Current.getValueAt(sel, 0).toString().toUpperCase();
					lbl_stud_showRemoveCourse.setText(ccode);
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
				}
			}
		});
		scrollPane_stud_courses.setViewportView(table_stud_courses);
		panel_student.add(scrollPane_stud_courses);

		comboBox_stud_grade.setBounds(472, 511, 108, 25);
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
		scrollPane_stud_finished.setBounds(856, 253, 367, 219);
		panel_student.add(scrollPane_stud_finished);
		table_stud_finished = new JTable();
		scrollPane_stud_finished.setViewportView(table_stud_finished);

		JLabel lblFinishedCourses = new JLabel("Finished Courses:");
		lblFinishedCourses.setBounds(866, 228, 108, 14);
		panel_student.add(lblFinishedCourses);

		JLabel lblCurrentCourses = new JLabel("Current Courses:");
		lblCurrentCourses.setBounds(472, 224, 108, 14);
		panel_student.add(lblCurrentCourses);

		JLabel lbl_student_removeStudentCourse = new JLabel("Remove student from selected course:");
		lbl_student_removeStudentCourse.setBounds(472, 558, 228, 14);
		panel_student.add(lbl_student_removeStudentCourse);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(472, 549, 248, 14);
		panel_student.add(separator_2);

		JButton btn_course_deleteSelCourse = new JButton("Delete");
		btn_course_deleteSelCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField_stud_pnr.getText().trim().isEmpty()) {
						lbl_feedback.setText(feedbackHandler.noInput());
					} else {
						int st = table_stud_courses.getSelectedRow();
						String ccode = dtmStud_Current.getValueAt(st, 0).toString();
						String spnr = textField_stud_pnr.getText();
						controller.deleteStudying(spnr, ccode);
						lbl_feedback.setText("Course successfully removed from student!");

					}
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
				}
			}

		});
		btn_course_deleteSelCourse.setBounds(745, 558, 85, 23);
		panel_student.add(btn_course_deleteSelCourse);
		DefaultComboBoxModel<String> ccodesList = new DefaultComboBoxModel<String>();

		DefaultTableModel dtmcourses = new DefaultTableModel();
		String[] course = { "Code", "Semester" };
		dtmcourses.setColumnIdentifiers(course);

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
						communicateMessage(feedbackHandler.noInput());
					} else {
						controller.deleteCourse(textField_course_delete_ccode.getText());
					}
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
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
						communicateMessage(feedbackHandler.noInput());
					} else {
						controller.createCourse(textField_course_courseCode.getText(), textField_course_cname.getText(),
								textField_course_points.getText());
						communicateMessage("Course successfully added!");
					}
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
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
					communicateMessage(feedbackHandler.noInput());
				} else {
					try {
						Course c = controller.getCourse(textField_course_delete_ccode.getText());
						if (c == null) {
							communicateMessage(feedbackHandler.noCourseFound(textField_course_delete_ccode.getText()));
						} else {
							textField_course_delete_cname.setText(c.getCname());
							textField_course_delete_points.setText(Integer.toString(c.getCpoint()));
						}
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
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
					communicateMessage(feedbackHandler.noInput());
				} else {
					try {
						ArrayList<Studied> r = controller.getCourseResult(textField_course_search_ccode.getText());
						dtmCourse_results.setRowCount(0);

						if (r == null) {
							communicateMessage(feedbackHandler.noStudentFound(textField_course_search_ccode.getText()));
							UtilView.clearNonSearchFields(regGradePanelFields);
						} else {

							for (Studied re : r) {
								String[] studentsCourses = { re.getsPnr(), re.getSemester().toUpperCase(),
										re.getGrade().toUpperCase() };
								dtmCourse_results.addRow(studentsCourses);
							}

							table_course.setModel(dtmCourse_results);
							lbl_course_search_showgradeA
									.setText(controller.acedIt(textField_course_search_ccode.getText()));
						}
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
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

		// ***************************************
		// ***********CRONUS ACCESS TAB***********
		// ***************************************

		table_caccess = new JTable();
		JPanel panel_caccess = new JPanel();
		tabbedPane.addTab("Cronus Access", panel_caccess);
		panel_caccess.setLayout(null);

		JLabel lbl_caccess_selectOption = new JLabel("Select query:");
		lbl_caccess_selectOption.setBounds(10, 22, LABEL_WIDTH, LABEL_HEIGHT);
		panel_caccess.add(lbl_caccess_selectOption);

		JComboBox<String> comboBox_caccess = new JComboBox(controllerCronus.getCronusQueryNames());
		comboBox_caccess.setBounds(119, 21, 228, 25);

		panel_caccess.add(comboBox_caccess);

		JButton btn_caccess_go = new JButton("Go");
		btn_caccess_go.setBounds(372, 22, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_caccess_go.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedCronusIndex = comboBox_caccess.getSelectedIndex();
				if (selectedCronusIndex != 0) {
					try {
						table_caccess.setModel(controllerCronus.getTableModel(selectedCronusIndex));

					} catch (SQLException e) {
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
					}
				}
			}
		});
		panel_caccess.add(btn_caccess_go);

		JScrollPane scrollPane_caccess = new JScrollPane();
		scrollPane_caccess.setBounds(119, 92, 544, 229);
		panel_caccess.add(scrollPane_caccess);

		table_caccess = new JTable();
		table_caccess.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_caccess.setViewportView(table_caccess);

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

	public void communicateMessage(String message) {
		lbl_feedback.setText(message);
	}
}
