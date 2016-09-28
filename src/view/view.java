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

import controller.ControllerCronus;
import controller.ControllerLu;
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
	private ControllerLu controllerLu = new ControllerLu();
	private ControllerCronus controllerCronus = new ControllerCronus();
	private FeedbackHandler feedbackHandler = new FeedbackHandler();
	private ExceptionHandler exceptionHandler = new ExceptionHandler();
	private ArrayList<JTextField> studPanelFields = new ArrayList<JTextField>();
	private ArrayList<JTextField> regGradePanelFields = new ArrayList<JTextField>();
	private ArrayList<JTextField> coursePanelFields = new ArrayList<JTextField>();
	JLabel lbl_feedback = new JLabel("");
	ButtonGroup btngr_course = new ButtonGroup();
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
	private JTextField textField_stud_findStudentAll_pnr;
	private JTextField textField_course_courseCode;
	private JTextField textField_course_cname;
	private JTextField textField_course_points;
	private JTable table_stud_currentCourses;
	private JTable table_caccess;
	private JTextField textField_stud_deleteAdd_pnr;
	private JTextField textField_stud_deleteAdd_name;
	private JTextField textField_stud_deleteAdd_address;
	private DefaultTableModel dtmNotFinished;
	private DefaultTableModel dtmcourse_showall;
	private DefaultTableModel dtmcourse_mostThrough;
	private JTable table_stud_foundStud;
	private JTable table_stud_finishedCourses;
	private JTable table_course;
	private JTextField textField_course_enrolled_ccode;
	private JTextField textField_stud_regOnCourse_pnr;
	private JTextField textField_stud_regOnCourse_ccode;
	private JTable table_stud_regOnCourse_courseList;
	// test branch

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

		JPanel panel_course = new JPanel();
		tabbedPane.addTab("Course", panel_course);
		panel_course.setLayout(null);

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

		JLabel lbl_course_ccode = new JLabel("Course code:");
		lbl_course_ccode.setBounds(10, 33, LABEL_WIDTH, LABEL_HEIGHT);
		panel_course.add(lbl_course_ccode);

		JLabel lbl_course_name = new JLabel("Course name:");
		lbl_course_name.setBounds(10, 79, LABEL_WIDTH, LABEL_HEIGHT);
		panel_course.add(lbl_course_name);

		JLabel lbl_course_points = new JLabel("Points:");
		lbl_course_points.setBounds(10, 128, LABEL_WIDTH, LABEL_HEIGHT);
		panel_course.add(lbl_course_points);

		JLabel lbl_course_addCourse_header = new JLabel("Add Course");
		lbl_course_addCourse_header.setBounds(10, 8, 80, 14);
		panel_course.add(lbl_course_addCourse_header);

		JLabel lbl_course_showGradeA_header = new JLabel("% of Students with grade A:");
		lbl_course_showGradeA_header.setBounds(674, 567, 167, 14);
		panel_course.add(lbl_course_showGradeA_header);

		JLabel lbl_course_showGradeA_result = new JLabel("");
		lbl_course_showGradeA_result.setBounds(851, 567, 46, 14);
		panel_course.add(lbl_course_showGradeA_result);

		JLabel lbl_course_enrolled_header = new JLabel("Enrolled Students");
		lbl_course_enrolled_header.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_course_enrolled_header.setBounds(674, 8, 189, 14);
		panel_course.add(lbl_course_enrolled_header);

		JLabel lbl_course_selectCourse_header = new JLabel("Select course:");
		lbl_course_selectCourse_header.setBounds(674, 38, 115, 14);
		panel_course.add(lbl_course_selectCourse_header);

		JLabel lbl_course_courseInfo_header = new JLabel("Course Info");
		lbl_course_courseInfo_header.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_course_courseInfo_header.setBounds(674, 146, 128, 14);
		panel_course.add(lbl_course_courseInfo_header);

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

		textField_course_enrolled_ccode = new JTextField();
		textField_course_enrolled_ccode.setColumns(10);
		textField_course_enrolled_ccode.setBounds(792, 33, 159, 25);
		panel_course.add(textField_course_enrolled_ccode);

		JScrollPane scrollPane_course = new JScrollPane();
		scrollPane_course.setBounds(674, 247, 527, 309);
		panel_course.add(scrollPane_course);

		table_course = new JTable();
		scrollPane_course.setViewportView(table_course);

		JButton btn_course_addCourse_clear = new JButton("Clear");
		btn_course_addCourse_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UtilView.clearAllFields(coursePanelFields);
			}
		});

		JRadioButton rdbtn_course_showAll = new JRadioButton("Show all courses");
		rdbtn_course_showAll.setBounds(674, 164, 109, 23);
		panel_course.add(rdbtn_course_showAll);
		btngr_course.add(rdbtn_course_showAll);

		JRadioButton rdbtn_course_highestThrough = new JRadioButton(
				"Only shows the course with the highest throughoutput", false);
		rdbtn_course_highestThrough.setBounds(674, 190, 360, 23);
		panel_course.add(rdbtn_course_highestThrough);
		btngr_course.add(rdbtn_course_highestThrough);

		JRadioButton rdbtn_course_showNotFinished = new JRadioButton(
				"Show only students who hasn't finished the course", false);
		rdbtn_course_showNotFinished.setBounds(674, 66, 301, 23);
		panel_course.add(rdbtn_course_showNotFinished);
		btngr_course.add(rdbtn_course_showNotFinished);
		btn_course_addCourse_clear.setBounds(82, 164, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_addCourse_clear);

		JButton btn_course_deleteAdd_delete = new JButton("Delete");
		btn_course_deleteAdd_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField_course_courseCode.getText().trim().isEmpty()) {
						communicateMessage(feedbackHandler.noInput());
					} else {
						controllerLu.deleteCourse(textField_course_courseCode.getText());
					}
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
				}
			}
		});
		btn_course_deleteAdd_delete.setBounds(318, 164, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_deleteAdd_delete);

		JButton btn_course_deleteAdd_add = new JButton("Add Course");
		btn_course_deleteAdd_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField_course_courseCode.getText().trim().isEmpty()) {
						communicateMessage(feedbackHandler.noInput());
					} else {
						controllerLu.createCourse(textField_course_courseCode.getText(),
								textField_course_cname.getText(), textField_course_points.getText());
						communicateMessage("Course successfully added!");
					}
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
				}
			}

		});
		btn_course_deleteAdd_add.setBounds(200, 164, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_deleteAdd_add);

		JButton btn_course_deleteAdd_search = new JButton("Search");
		btn_course_deleteAdd_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textField_course_courseCode.getText().trim().isEmpty()) {
					communicateMessage(feedbackHandler.noInput());
				} else {
					try {
						Course c = controllerLu.getCourse(textField_course_courseCode.getText());
						if (c == null) {
							communicateMessage(feedbackHandler.noCourseFound(textField_course_courseCode.getText()));
						} else {
							textField_course_cname.setText(c.getCname());
							textField_course_points.setText(Integer.toString(c.getCpoint()));
						}
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
					}
				}
			}
		});
		btn_course_deleteAdd_search.setBounds(318, 34, 108, 23);
		panel_course.add(btn_course_deleteAdd_search);

		JButton btn_course_courseInfo_showResult = new JButton("Show Result");
		btn_course_courseInfo_showResult.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtn_course_showAll.isSelected()) {
					try {
						ArrayList<Course> c = controllerLu.allCourses();
						dtmcourse_showall.setRowCount(0);

						for (Course co : c) {
							String[] allCourses = { co.getCcode().toUpperCase(), co.getCname(),
									Integer.toString(co.getCpoint()) };
							dtmcourse_showall.addRow(allCourses);
						}
						table_course.setModel(dtmcourse_showall);
					} catch (Exception e) {

					}
				} else {
					if (rdbtn_course_highestThrough.isSelected()) {
						try {
							ArrayList<Course> c = controllerLu.mostThrough();
							dtmcourse_mostThrough.setRowCount(0);

							for (Course co : c) {
								String[] allCourses = { co.getCcode().toUpperCase(), Integer.toString(co.getTotal()) };
								dtmcourse_mostThrough.addRow(allCourses);
							}
							table_course.setModel(dtmcourse_mostThrough);
						} catch (Exception e) {
						}
					}
				}
			}

		});
		btn_course_courseInfo_showResult.setBounds(1093, 213, 108, 23);
		panel_course.add(btn_course_courseInfo_showResult);

		JButton btn_course_enrolled_showResult = new JButton("Show Result");
		btn_course_enrolled_showResult.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (textField_course_enrolled_ccode.getText().trim().isEmpty()) {
					communicateMessage(feedbackHandler.noInput());
				} else {
					try {
						if (rdbtn_course_showNotFinished.isSelected()) {
							ArrayList<Studying> s = controllerLu
									.notFinished(textField_course_enrolled_ccode.getText().trim());
							dtmNotFinished.setRowCount(0);

							for (Studying studying : s) {
								String[] studentsCourses = { studying.getsPnr(), studying.getSemester().toUpperCase() };
								dtmNotFinished.addRow(studentsCourses);
							}
							table_course.setModel(dtmNotFinished);

						} else {
							ArrayList<Studied> r = controllerLu
									.getCourseResult(textField_course_enrolled_ccode.getText());
							dtmCourse_results.setRowCount(0);

							if (r == null) {
								communicateMessage(
										feedbackHandler.noCourseFound(textField_course_enrolled_ccode.getText()));
								UtilView.clearNonSearchFields(regGradePanelFields);
							} else {

								for (Studied re : r) {
									String[] studentsCourses = { re.getsPnr(), re.getSemester().toUpperCase(),
											re.getGrade().toUpperCase() };
									dtmCourse_results.addRow(studentsCourses);
								}

								table_course.setModel(dtmCourse_results);
								lbl_course_showGradeA_result
										.setText(controllerLu.acedIt(textField_course_enrolled_ccode.getText()));
							}
						}
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
					}
				}

			}
		});
		btn_course_enrolled_showResult.setBounds(1093, 110, 108, 23);
		panel_course.add(btn_course_enrolled_showResult);

		JButton btn_stud_deleteAdd_search = new JButton("Search");
		btn_stud_deleteAdd_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textField_stud_deleteAdd_pnr.getText().trim().isEmpty()) {
					communicateMessage(feedbackHandler.noInput());
				} else {
					try {
						Student s = controllerLu.getStudent(textField_stud_deleteAdd_pnr.getText());
						if (s == null) {
							communicateMessage(feedbackHandler.noStudentFound(textField_stud_deleteAdd_pnr.getText()));
						} else {
							textField_stud_deleteAdd_name.setText(s.getSname());
							textField_stud_deleteAdd_address.setText(s.getSaddress());
						}
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
					}
				}
			}
		});

		JLabel lbl_stud_deleteAdd_header = new JLabel("Add/Delete Student");
		lbl_stud_deleteAdd_header.setBounds(12, 11, LABEL_HEADLINE_WIDTH, LABEL_HEADLINE_HEIGHT);
		panel_student.add(lbl_stud_deleteAdd_header);

		JLabel lbl_stud_deleteAdd_pnr = new JLabel("Personal number");
		lbl_stud_deleteAdd_pnr.setBounds(12, 47, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_deleteAdd_pnr);

		JLabel lbl_stud_deleteAdd_name = new JLabel("Name:");
		lbl_stud_deleteAdd_name.setBounds(12, 83, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_deleteAdd_name);

		JLabel lbl_stud_deleteAdd_address = new JLabel("Address:");
		lbl_stud_deleteAdd_address.setBounds(12, 119, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_deleteAdd_address);

		JLabel lbl_stud_regOnCourse_header = new JLabel("Registrer student to course");
		lbl_stud_regOnCourse_header.setBounds(12, 228, 254, 14);
		panel_student.add(lbl_stud_regOnCourse_header);

		JLabel lbl_stud_regOnCourse_pnr = new JLabel("Personal number:");
		lbl_stud_regOnCourse_pnr.setBounds(12, 264, 108, 23);
		panel_student.add(lbl_stud_regOnCourse_pnr);

		JLabel lbl_stud_regOnCourse_ccode = new JLabel("Course code:");
		lbl_stud_regOnCourse_ccode.setBounds(12, 300, 108, 23);
		panel_student.add(lbl_stud_regOnCourse_ccode);

		JLabel lbl_stud_regOnCourse_selectFromList = new JLabel("Or select Course from list:");
		lbl_stud_regOnCourse_selectFromList.setBounds(12, 349, 216, 14);
		panel_student.add(lbl_stud_regOnCourse_selectFromList);

		JLabel lbl_stud_findStudentAll_search = new JLabel("Search Student");
		lbl_stud_findStudentAll_search.setBounds(472, 11, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_findStudentAll_search);

		JLabel lbl_stud_findStudentAll_pnr = new JLabel("Personal number:");
		lbl_stud_findStudentAll_pnr.setBounds(472, 47, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_findStudentAll_pnr);

		JLabel lbl_stud_findStudentAll_ccode = new JLabel("Course code: ");
		lbl_stud_findStudentAll_ccode.setBounds(472, 83, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_findStudentAll_ccode);

		JLabel lbl_stud_removeStudentCourse_selected = new JLabel("");
		lbl_stud_removeStudentCourse_selected.setBounds(693, 558, 58, 14);
		panel_student.add(lbl_stud_removeStudentCourse_selected);

		JLabel lbl_stud_removeStudentCourse_header = new JLabel("Remove student from selected course:");
		lbl_stud_removeStudentCourse_header.setBounds(472, 558, 228, 14);
		panel_student.add(lbl_stud_removeStudentCourse_header);

		JLabel lbl_stud_regGrade_header = new JLabel("Register grade for selected course");
		lbl_stud_regGrade_header.setBounds(472, 483, 248, 23);
		panel_student.add(lbl_stud_regGrade_header);

		JLabel lbl_stud_finishedCourses_header = new JLabel("Finished Courses:");
		lbl_stud_finishedCourses_header.setBounds(856, 228, 108, 14);
		panel_student.add(lbl_stud_finishedCourses_header);

		JLabel lbl_stud_currentCourses_header = new JLabel("Current Courses: ");
		lbl_stud_currentCourses_header.setBounds(472, 228, 108, 14);
		panel_student.add(lbl_stud_currentCourses_header);

		textField_stud_findStudentAll_pnr = new JTextField();
		textField_stud_findStudentAll_pnr.setBounds(592, 46, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_findStudentAll_pnr);
		textField_stud_findStudentAll_pnr.setColumns(10);
		studPanelFields.add(textField_stud_findStudentAll_pnr);

		textField_stud_deleteAdd_pnr = new JTextField();
		textField_stud_deleteAdd_pnr.setBounds(132, 46, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_deleteAdd_pnr);
		textField_stud_deleteAdd_pnr.setColumns(10);

		textField_stud_deleteAdd_name = new JTextField();
		textField_stud_deleteAdd_name.setBounds(132, 82, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_deleteAdd_name);
		textField_stud_deleteAdd_name.setColumns(10);

		textField_stud_deleteAdd_address = new JTextField();
		textField_stud_deleteAdd_address.setBounds(132, 118, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_deleteAdd_address);
		textField_stud_deleteAdd_address.setColumns(10);

		textField_stud_regOnCourse_pnr = new JTextField();
		textField_stud_regOnCourse_pnr.setColumns(10);
		textField_stud_regOnCourse_pnr.setBounds(132, 263, 159, 25);
		panel_student.add(textField_stud_regOnCourse_pnr);

		textField_stud_regOnCourse_ccode = new JTextField();
		textField_stud_regOnCourse_ccode.setColumns(10);
		textField_stud_regOnCourse_ccode.setBounds(132, 299, 159, 25);
		panel_student.add(textField_stud_regOnCourse_ccode);

		JScrollPane scrollPane_stud_foundStudent = new JScrollPane();
		scrollPane_stud_foundStudent.setBounds(472, 121, 391, 88);
		table_stud_foundStud = new JTable();
		scrollPane_stud_foundStudent.setViewportView(table_stud_foundStud);
		panel_student.add(scrollPane_stud_foundStudent);

		JScrollPane scrollPane_stud_currentCourses = new JScrollPane();
		scrollPane_stud_currentCourses.setBounds(472, 253, 358, 219);
		table_stud_currentCourses = new JTable();
		table_stud_currentCourses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int sel = table_stud_currentCourses.getSelectedRow();
					String ccode = dtmStud_Current.getValueAt(sel, 0).toString().toUpperCase();
					lbl_stud_removeStudentCourse_selected.setText(ccode);
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
				}
			}
		});
		scrollPane_stud_currentCourses.setViewportView(table_stud_currentCourses);
		panel_student.add(scrollPane_stud_currentCourses);

		JScrollPane scrollPane_stud_finishedCourses = new JScrollPane();
		scrollPane_stud_finishedCourses.setBounds(856, 253, 367, 219);
		panel_student.add(scrollPane_stud_finishedCourses);
		table_stud_finishedCourses = new JTable();
		scrollPane_stud_finishedCourses.setViewportView(table_stud_finishedCourses);

		JScrollPane scrollPane_stud_regOnCourse_courseList = new JScrollPane();
		scrollPane_stud_regOnCourse_courseList.setBounds(12, 374, 397, 173);
		panel_student.add(scrollPane_stud_regOnCourse_courseList);

		table_stud_regOnCourse_courseList = new JTable();
		scrollPane_stud_regOnCourse_courseList.setViewportView(table_stud_regOnCourse_courseList);

		JComboBox<String> comboBox_stud_grade_1 = new JComboBox<String>();

		comboBox_stud_grade_1.setBounds(472, 511, 108, 25);
		panel_student.add(comboBox_stud_grade_1);
		comboBox_stud_grade_1.addItem("A");
		comboBox_stud_grade_1.addItem("B");
		comboBox_stud_grade_1.addItem("C");
		comboBox_stud_grade_1.addItem("D");
		comboBox_stud_grade_1.addItem("E");
		comboBox_stud_grade_1.addItem("U");

		JComboBox<String> comboBox_stud_course_1 = new JComboBox<String>();

		comboBox_stud_course_1.setToolTipText("");
		comboBox_stud_course_1.setBounds(592, 82, COMBOBOX_WIDHT, COMBOBOX_HEIGHT);
		panel_student.add(comboBox_stud_course_1);

		btn_stud_deleteAdd_search.setBounds(301, 47, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_deleteAdd_search);

		JButton btn_stud_deleteAdd_clear = new JButton("Clear");
		btn_stud_deleteAdd_clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UtilView.clearAllFields(studPanelFields);
			}
		});
		btn_stud_deleteAdd_clear.setBounds(41, 155, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_deleteAdd_clear);

		JButton btn_stud_deleteAdd_delete = new JButton("Delete");
		btn_stud_deleteAdd_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField_stud_deleteAdd_pnr.getText().trim().isEmpty()) {
						communicateMessage(feedbackHandler.noInput());
					} else {
						controllerLu.deleteStudent(textField_stud_deleteAdd_pnr.getText());
					}
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
				}
			}
		});
		btn_stud_deleteAdd_delete.setBounds(301, 155, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_deleteAdd_delete);

		JButton btn_stud_deleteAdd_add = new JButton("Add new student");
		btn_stud_deleteAdd_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField_stud_deleteAdd_pnr.getText().trim().isEmpty()
							|| textField_stud_deleteAdd_name.getText().trim().isEmpty()
							|| textField_stud_deleteAdd_address.getText().trim().isEmpty()) {
						communicateMessage(feedbackHandler.insufficientInput());
					} else {
						boolean test = controllerLu.createStudent(textField_stud_deleteAdd_pnr.getText(),
								textField_stud_deleteAdd_name.getText(), textField_stud_deleteAdd_address.getText());
						if (test == false) {
							communicateMessage(feedbackHandler.studentAdded(textField_stud_deleteAdd_pnr.getText()));
						} else {
							communicateMessage("NOT SO GREAT!");
						}
					}
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
				}
			}

		});
		btn_stud_deleteAdd_add.setBounds(159, 155, 132, 23);
		panel_student.add(btn_stud_deleteAdd_add);

		JButton btn_stud_findStudentAll_register = new JButton("Register Grade");
		btn_stud_findStudentAll_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField_stud_findStudentAll_pnr.getText().trim().isEmpty()) {
						lbl_feedback.setText(feedbackHandler.noInput());
					} else {

						String grade = comboBox_stud_grade_1.getSelectedItem().toString();
						int st = table_stud_currentCourses.getSelectedRow();
						String semester = dtmStud_Current.getValueAt(st, 1).toString();
						String ccode = dtmStud_Current.getValueAt(st, 0).toString();
						String spnr = textField_stud_findStudentAll_pnr.getText();
						controllerLu.registerGrade(semester, spnr, ccode, grade);
						controllerLu.deleteStudying(spnr, ccode);
						lbl_feedback.setText("Grade registered!");
					}
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
				}
			}

		});

		JButton btn_stud_regOnCourse_search = new JButton("Search");
		btn_stud_regOnCourse_search.setBounds(301, 264, 108, 23);
		panel_student.add(btn_stud_regOnCourse_search);

		JButton btn_stud_regOnCourse_register = new JButton("Register");
		btn_stud_regOnCourse_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField_stud_regOnCourse_pnr.getText().trim().isEmpty()) {
						lbl_feedback.setText(feedbackHandler.noInput());
					} else {

						String spnr = textField_stud_regOnCourse_pnr.getText();
						String ccode = textField_stud_regOnCourse_ccode.getText();
						String semester = "ht16";
						controllerLu.registerOnCourse(spnr, ccode, semester);
						lbl_feedback.setText("Student registered to course!");
					}
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
				}
			}
		});
		btn_stud_regOnCourse_register.setBounds(301, 558, 114, 23);
		panel_student.add(btn_stud_regOnCourse_register);

		JButton btn_stud_findStudentAll_search = new JButton("Search");
		btn_stud_findStudentAll_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearFeedback();
				String spnr = textField_stud_findStudentAll_pnr.getText().trim();

				if (spnr.isEmpty()) {
					communicateMessage(feedbackHandler.noInput());
				} else {
					try {
						table_stud_foundStud.setModel(controllerLu.getTestStudent(spnr));
						table_stud_currentCourses.setModel(controllerLu.getTestStudentStudying(spnr));
						table_stud_finishedCourses.setModel(controllerLu.getTestStudentStudied(spnr));
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
					}
				}
			}
		});
		btn_stud_findStudentAll_search.setBounds(763, 47, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_findStudentAll_search);
		btn_stud_findStudentAll_register.setBounds(592, 512, 139, 23);
		panel_student.add(btn_stud_findStudentAll_register);

		JButton btn_stud_findStudentAll_delete = new JButton("Delete");
		btn_stud_findStudentAll_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField_stud_findStudentAll_pnr.getText().trim().isEmpty()) {
						lbl_feedback.setText(feedbackHandler.noInput());
					} else {
						int st = table_stud_currentCourses.getSelectedRow();
						String ccode = dtmStud_Current.getValueAt(st, 0).toString();
						String spnr = textField_stud_findStudentAll_pnr.getText();
						controllerLu.deleteStudying(spnr, ccode);
						lbl_feedback.setText("Course successfully removed from student!");

					}
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
				}
			}

		});

		// ***********************************
		// ************COURSE TAB*************
		// ***********************************
		btn_stud_findStudentAll_delete.setBounds(745, 558, 85, 23);
		panel_student.add(btn_stud_findStudentAll_delete);

		JSeparator separator_stud_vertical = new JSeparator();
		separator_stud_vertical.setOrientation(SwingConstants.VERTICAL);
		separator_stud_vertical.setBounds(438, 11, 2, 582);
		panel_student.add(separator_stud_vertical);

		JSeparator separator_stud_regGrade = new JSeparator();
		separator_stud_regGrade.setBounds(472, 549, 358, 14);
		panel_student.add(separator_stud_regGrade);

		DefaultComboBoxModel<String> ccodesList = new DefaultComboBoxModel<String>();

		DefaultTableModel dtmcourses = new DefaultTableModel();
		String[] course = { "Code", "Semester" };
		dtmcourses.setColumnIdentifiers(course);

		DefaultTableModel dtmcourse_showall = new DefaultTableModel();
		String[] courses = { "Code", "Name", "Points" };
		dtmcourse_showall.setColumnIdentifiers(courses);

		DefaultTableModel dtmcourse_mostThrough = new DefaultTableModel();
		String[] mcourses = { "Code", "No. of students through ", };
		dtmcourse_mostThrough.setColumnIdentifiers(mcourses);

		DefaultTableModel dtmNotFinished = new DefaultTableModel();
		String[] students = { "Personal Number", "Semester" };
		dtmNotFinished.setColumnIdentifiers(students);

		// ***************************************
		// ***********CRONUS ACCESS TAB***********
		// ***************************************

		table_caccess = new JTable();
		JPanel panel_caccess = new JPanel();
		tabbedPane.addTab("Cronus Access", panel_caccess);
		panel_caccess.setLayout(null);

		JLabel lbl_caccess_selectOption = new JLabel("Tables");
		lbl_caccess_selectOption.setBounds(22, 22, 87, 23);
		panel_caccess.add(lbl_caccess_selectOption);

		JComboBox<String> comboBox_caccessTables = new JComboBox(controllerCronus.getCronusQueryNamesTables());
		comboBox_caccessTables.setBounds(119, 21, 297, 25);

		panel_caccess.add(comboBox_caccessTables);

		JButton btn_caccess_go_tables = new JButton("Show tables");
		btn_caccess_go_tables.setBounds(479, 22, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_caccess_go_tables.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedCronusIndex = comboBox_caccessTables.getSelectedIndex();
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
		panel_caccess.add(btn_caccess_go_tables);

		JScrollPane scrollPane_caccess = new JScrollPane();
		scrollPane_caccess.setBounds(119, 189, 544, 229);
		panel_caccess.add(scrollPane_caccess);

		table_caccess = new JTable();
		scrollPane_caccess.setViewportView(table_caccess);

		JComboBox<String> comboBox_caccessMeta = new JComboBox(controllerCronus.getCronusQueryNamesMetaData());
		comboBox_caccessMeta.setBounds(119, 89, 297, 25);

		panel_caccess.add(comboBox_caccessMeta);

		JButton btn_caccess_go_meta = new JButton("Show meta");
		btn_caccess_go_meta.setBounds(479, 88, 108, 23);
		btn_caccess_go_meta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedCronusIndex = comboBox_caccessMeta.getSelectedIndex();
				if (selectedCronusIndex != 0) {
					try {
						table_caccess.setModel(controllerCronus.getTableModelMeta(selectedCronusIndex));

					} catch (SQLException e) {
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
					}
				}
			}
		});
		panel_caccess.add(btn_caccess_go_meta);

		JLabel lblNewLabel = new JLabel("Meta data");
		lblNewLabel.setBounds(22, 89, 75, 19);
		panel_caccess.add(lblNewLabel);

		// ********************************
		// ****** CRONUS OPEN FILES *******
		// ********************************

		JPanel panel = new JPanel();
		tabbedPane.addTab("Cronus Database", null, panel, null);
		panel.setLayout(null);

		JLabel lblSelectOption = new JLabel("Select option:");
		lblSelectOption.setBounds(44, 95, 112, 39);
		panel.add(lblSelectOption);

		JLabel lblSelectQuery = new JLabel("Select query:");
		lblSelectQuery.setBounds(44, 150, 112, 39);
		panel.add(lblSelectQuery);

		String[] microsoft = { "", "Access", "Excel" };

		JComboBox comboBox_access_excel = new JComboBox(microsoft);
		comboBox_access_excel.setName("comboBox_access_excel");
		comboBox_access_excel.setBounds(171, 101, 280, 26);
		panel.add(comboBox_access_excel);

		JComboBox comboBox_cdatabase_query = new JComboBox(controllerCronus.getCronusFileNameToOpen());
		comboBox_cdatabase_query.setName("comboBox_cdatabase_query");
		comboBox_cdatabase_query.setBounds(171, 156, 280, 26);
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

		String[] micoffice = { "", "Access", "Excel", "Word" };

		JComboBox comboBox_oform_selectProgram = new JComboBox(micoffice);
		comboBox_oform_selectProgram.setName("comboBox_oform_selectProgram");
		comboBox_oform_selectProgram.setBounds(171, 412, 144, 26);
		panel.add(comboBox_oform_selectProgram);

		JLabel lbl_oform_select = new JLabel("Select :");
		lbl_oform_select.setBounds(30, 466, 112, 39);
		panel.add(lbl_oform_select);

		String[] ec = { " ", "Employee", "Customer" };

		JComboBox comboBox_oform_selectQuery = new JComboBox(ec);
		comboBox_oform_selectQuery.setName("comboBox_oform_selectQuery");
		comboBox_oform_selectQuery.setBounds(171, 472, 144, 26);
		panel.add(comboBox_oform_selectQuery);

		JButton btnGo = new JButton("GO 1");
		btnGo.setBounds(40, 224, 102, 29);
		panel.add(btnGo);
		btnGo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox_access_excel.getSelectedIndex() != 0 && comboBox_cdatabase_query.getSelectedIndex() != 0) {
					controllerCronus.openCronusFile(comboBox_access_excel.getName(),
							comboBox_access_excel.getSelectedIndex(), comboBox_cdatabase_query.getSelectedIndex());
				}
			}
		});

		JButton button_1 = new JButton("GO 2");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox_oform_selectProgram.getSelectedIndex() != 0
						&& comboBox_oform_selectQuery.getSelectedIndex() != 0) {
					controllerCronus.openCronusFile(comboBox_oform_selectProgram.getName(),
							comboBox_oform_selectProgram.getSelectedIndex(),
							comboBox_oform_selectQuery.getSelectedIndex());
				}
			}
		});
		button_1.setBounds(30, 546, 102, 29);
		panel.add(button_1);

	}

	private void communicateMessage(String message) {
		lbl_feedback.setText(message);
	}

	private void clearFeedback() {
		lbl_feedback.setText("");
	}
}
