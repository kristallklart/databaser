package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
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
	private ArrayList<JTextField> coursePanelFields = new ArrayList<JTextField>();
	private JLabel lbl_feedback = new JLabel("");
	private ButtonGroup btngr_course = new ButtonGroup();
	private DefaultTableModel emptyDefTableModel = new DefaultTableModel();
	private DefaultTableModel dtm_stud_finishedCourses = new DefaultTableModel();
	private DefaultTableModel dtm_stud_currentCourses = new DefaultTableModel();
	private DefaultTableModel dtm_stud_student = new DefaultTableModel();
	private JTable table_stud_currentCourses = new JTable(dtm_stud_currentCourses);
	private JPanel contentPane;
	private final int BUTTON_WIDTH = 108;
	private final int BUTTON_HEIGHT = 23;
	private final int TEXTFIELD_WIDTH = 159;
	private final int TEXTFIELD_HEIGHT = 25;
	private final int LABEL_WIDTH = 108;
	private final int LABEL_HEIGHT = 23;
	private final int LABEL_HEADLINE_WIDTH = 150;
	private final int LABEL_HEADLINE_HEIGHT = 23;
	private JTextField textField_stud_findStudentAll_pnr;
	private JTextField textField_course_courseCode;
	private JTextField textField_course_cname;
	private JTextField textField_course_points;
	private JTable table_caccess;
	private JTextField textField_stud_deleteAdd_pnr;
	private JTextField textField_stud_deleteAdd_name;
	private JTextField textField_stud_deleteAdd_address;
	private JTable table_stud_foundStud;
	private JTable table_stud_finishedCourses;
	private JTable table_course;
	private JTextField textField_course_enrolled_ccode;
	private JTextField textField_stud_regOnCourse_pnr;
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

		JPanel panel_student = new JPanel();
		tabbedPane.addTab("Student", panel_student);
		panel_student.setLayout(null);

		JPanel panel_course = new JPanel();
		tabbedPane.addTab("Course", panel_course);
		panel_course.setLayout(null);

		lbl_feedback.setBounds(10, 640, 1244, 20);
		contentPane.add(lbl_feedback);

		DefaultTableModel dtmCourse_results = new DefaultTableModel();
		String[] resultsCourse = { "Personal Number", "Semester", "Grade" };
		dtmCourse_results.setColumnIdentifiers(resultsCourse);

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

		// ***********************************
		// ************COURSE TAB*************
		// ***********************************

		JLabel lbl_course_ccode = new JLabel("Course code:");
		lbl_course_ccode.setBounds(10, 33, LABEL_WIDTH, LABEL_HEIGHT);
		panel_course.add(lbl_course_ccode);

		JLabel lbl_course_name = new JLabel("Course name:");
		lbl_course_name.setBounds(10, 67, LABEL_WIDTH, LABEL_HEIGHT);
		panel_course.add(lbl_course_name);

		JLabel lbl_course_points = new JLabel("Points:");
		lbl_course_points.setBounds(10, 101, LABEL_WIDTH, LABEL_HEIGHT);
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
		lbl_course_courseInfo_header.setBounds(674, 166, 128, 14);
		panel_course.add(lbl_course_courseInfo_header);

		textField_course_courseCode = new JTextField();
		textField_course_courseCode.setColumns(10);
		textField_course_courseCode.setBounds(106, 32, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_course.add(textField_course_courseCode);
		coursePanelFields.add(textField_course_courseCode);

		textField_course_cname = new JTextField();
		textField_course_cname.setColumns(10);
		textField_course_cname.setBounds(106, 66, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_course.add(textField_course_cname);
		coursePanelFields.add(textField_course_cname);

		textField_course_points = new JTextField();
		textField_course_points.setColumns(10);
		textField_course_points.setBounds(106, 101, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
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
		table_course.setName("table_course");
		scrollPane_course.setViewportView(table_course);

		JButton btn_course_addCourse_clear = new JButton("Clear");
		btn_course_addCourse_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UtilView.clearFields(coursePanelFields);
			}
		});

		JRadioButton rdbtn_course_showAll = new JRadioButton("Show all courses");
		rdbtn_course_showAll.setBounds(674, 187, 128, 23);
		panel_course.add(rdbtn_course_showAll);
		btngr_course.add(rdbtn_course_showAll);

		JRadioButton rdbtn_course_highestThrough = new JRadioButton(
				"Only shows the course with the highest throughoutput", false);
		rdbtn_course_highestThrough.setBounds(674, 213, 360, 23);
		panel_course.add(rdbtn_course_highestThrough);
		btngr_course.add(rdbtn_course_highestThrough);

		JRadioButton rdbtn_course_showNotFinished = new JRadioButton(
				"Show only students who hasn't finished the course", false);
		rdbtn_course_showNotFinished.setBounds(674, 66, 360, 23);
		panel_course.add(rdbtn_course_showNotFinished);
		btngr_course.add(rdbtn_course_showNotFinished);
		btn_course_addCourse_clear.setBounds(39, 135, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_addCourse_clear);

		JButton btn_course_deleteAdd_delete = new JButton("Delete");
		btn_course_deleteAdd_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField_course_courseCode.getText().trim().isEmpty()) {
						communicateMessage(feedbackHandler.noInputCcode());
					} else {
						controllerLu.deleteCourse(textField_course_courseCode.getText());
					}
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
				}
			}
		});
		btn_course_deleteAdd_delete.setBounds(275, 135, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_deleteAdd_delete);

		JButton btn_course_deleteAdd_add = new JButton("Add Course");
		btn_course_deleteAdd_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearFeedback();
				String ccode = textField_course_courseCode.getText().trim();
				String cname = textField_course_cname.getText().trim();
				int points = Integer.parseInt(textField_course_points.getText().trim());
				if (ccode.isEmpty() || cname.isEmpty()) {
					communicateMessage(feedbackHandler.insufficientInput());
				} else {
					ArrayList<Object> values = new ArrayList<Object>();
					values.add(ccode);
					values.add(cname);
					values.add(points);
					try {
						controllerLu.createAll(values, "Course");
						communicateMessage(feedbackHandler.courseAdded(ccode));
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
					}
				}
			}

		});
		btn_course_deleteAdd_add.setBounds(157, 135, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_deleteAdd_add);

		JButton btn_course_deleteAdd_search = new JButton("Search");
		btn_course_deleteAdd_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textField_course_courseCode.getText().trim().isEmpty()) {
					communicateMessage(feedbackHandler.noInputCcode());
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
		btn_course_deleteAdd_search.setBounds(275, 33, 108, 23);
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
					communicateMessage(feedbackHandler.noInputCcode());
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
		btn_course_enrolled_showResult.setBounds(961, 33, 108, 23);
		panel_course.add(btn_course_enrolled_showResult);

		// ***********************************
		// ***********STUDENT TAB*************
		// ***********************************

		JButton btn_stud_deleteAdd_search = new JButton("Search");
		btn_stud_deleteAdd_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearFeedback();
				String spnr = textField_stud_deleteAdd_pnr.getText().trim();
				if (spnr.isEmpty()) {
					communicateMessage(feedbackHandler.noInputPnr());
				} else {
					try {
						Student s = controllerLu.getStudent(spnr);
						textField_stud_deleteAdd_name.setText(s.getSname());
						textField_stud_deleteAdd_address.setText(s.getSaddress());
						communicateMessage(feedbackHandler.studentFound());
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
						UtilView.clearFields(studPanelFields);
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
		lbl_stud_regOnCourse_header.setBounds(12, 278, 254, 14);
		panel_student.add(lbl_stud_regOnCourse_header);

		JLabel lbl_stud_regOnCourse_pnr = new JLabel("Personal number:");
		lbl_stud_regOnCourse_pnr.setBounds(12, 314, 108, 23);
		panel_student.add(lbl_stud_regOnCourse_pnr);

		JLabel lbl_stud_regOnCourse_selectFromList = new JLabel("Or select Course from list:");
		lbl_stud_regOnCourse_selectFromList.setBounds(12, 349, 216, 14);
		panel_student.add(lbl_stud_regOnCourse_selectFromList);

		JLabel lbl_stud_findStudentAll_search = new JLabel("Search Student");
		lbl_stud_findStudentAll_search.setBounds(472, 11, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_findStudentAll_search);

		JLabel lbl_stud_findStudentAll_pnr = new JLabel("Personal number:");
		lbl_stud_findStudentAll_pnr.setBounds(472, 47, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_findStudentAll_pnr);

		JLabel lbl_stud_removeStudentCourse_header = new JLabel("Remove student from selected course");
		lbl_stud_removeStudentCourse_header.setBounds(472, 562, 228, 14);
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
		studPanelFields.add(textField_stud_deleteAdd_name);
		panel_student.add(textField_stud_deleteAdd_name);
		textField_stud_deleteAdd_name.setColumns(10);

		textField_stud_deleteAdd_address = new JTextField();
		textField_stud_deleteAdd_address.setBounds(132, 118, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		studPanelFields.add(textField_stud_deleteAdd_address);
		panel_student.add(textField_stud_deleteAdd_address);
		textField_stud_deleteAdd_address.setColumns(10);

		textField_stud_regOnCourse_pnr = new JTextField();
		textField_stud_regOnCourse_pnr.setColumns(10);
		textField_stud_regOnCourse_pnr.setBounds(132, 313, 159, 25);
		panel_student.add(textField_stud_regOnCourse_pnr);

		JScrollPane scrollPane_stud_foundStudent = new JScrollPane();
		scrollPane_stud_foundStudent.setBounds(472, 121, 391, 88);
		table_stud_foundStud = new JTable();
		table_stud_foundStud.setName("table_stud_foundStud");
		scrollPane_stud_foundStudent.setViewportView(table_stud_foundStud);
		panel_student.add(scrollPane_stud_foundStudent);

		JScrollPane scrollPane_stud_currentCourses = new JScrollPane();
		scrollPane_stud_currentCourses.setBounds(472, 253, 358, 219);
		table_stud_currentCourses = new JTable();
		table_stud_currentCourses.setName("table_stud_currentCourses");
		scrollPane_stud_currentCourses.setViewportView(table_stud_currentCourses);
		panel_student.add(scrollPane_stud_currentCourses);

		JScrollPane scrollPane_stud_finishedCourses = new JScrollPane();
		scrollPane_stud_finishedCourses.setBounds(856, 253, 367, 219);
		panel_student.add(scrollPane_stud_finishedCourses);
		table_stud_finishedCourses = new JTable();
		table_stud_finishedCourses.setName("table_stud_finishedCourses");
		scrollPane_stud_finishedCourses.setViewportView(table_stud_finishedCourses);

		JScrollPane scrollPane_stud_regOnCourse_courseList = new JScrollPane();
		scrollPane_stud_regOnCourse_courseList.setBounds(12, 374, 397, 173);
		panel_student.add(scrollPane_stud_regOnCourse_courseList);
		table_stud_regOnCourse_courseList = new JTable();
		table_stud_regOnCourse_courseList.setName("table_stud_regOnCourse_courseList");
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

		btn_stud_deleteAdd_search.setBounds(301, 47, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_deleteAdd_search);

		JButton btn_stud_deleteAdd_clear = new JButton("Clear");
		btn_stud_deleteAdd_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UtilView.clearFields(studPanelFields);
			}
		});
		btn_stud_deleteAdd_clear.setBounds(41, 155, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_deleteAdd_clear);

		JButton btn_stud_deleteAdd_delete = new JButton("Delete");
		btn_stud_deleteAdd_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearFeedback();
				String spnr = textField_stud_deleteAdd_pnr.getText().trim();
				if (spnr.isEmpty()) {
					communicateMessage(feedbackHandler.noPnrDelete());
				} else {
					try {
						controllerLu.deleteStudent(spnr);
						communicateMessage(feedbackHandler.studentDeleted(spnr));
						UtilView.clearFields(studPanelFields);
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
					}
				}
			}
		});
		btn_stud_deleteAdd_delete.setBounds(301, 155, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_deleteAdd_delete);

		JButton btn_stud_deleteAdd_add = new JButton("Add new student");
		btn_stud_deleteAdd_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearFeedback();
				String spnr = textField_stud_deleteAdd_pnr.getText().trim();
				String name = textField_stud_deleteAdd_name.getText().trim();
				String address = textField_stud_deleteAdd_address.getText().trim();

				if (spnr.isEmpty() || name.isEmpty() || address.isEmpty()) {
					communicateMessage(feedbackHandler.insufficientInput());
				} else {
					Student s = new Student(spnr, name, address);
					try {
						controllerLu.addStudent(s);
						communicateMessage(feedbackHandler.studentAdded(spnr));
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
					}
				}

			}
		});
		btn_stud_deleteAdd_add.setBounds(159, 155, 132, 23);
		panel_student.add(btn_stud_deleteAdd_add);

		JButton btn_stud_findStudentAll_register = new JButton("Register Grade");
		btn_stud_findStudentAll_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String spnr = textField_stud_findStudentAll_pnr.getText().trim();
				if (spnr.isEmpty()) {
					communicateMessage(feedbackHandler.insufficientInput());
				} else {
					try {
						int selectedRow = table_stud_currentCourses.getSelectedRow();
						String grade = comboBox_stud_grade_1.getSelectedItem().toString();
						String ccode = (String) table_stud_currentCourses.getValueAt(selectedRow, 0);
						String semester = (String) table_stud_currentCourses.getValueAt(selectedRow, 1);
						Studied s = new Studied(spnr, semester, grade, ccode);
						controllerLu.registerGrade(s);
						controllerLu.deleteStudying(spnr, ccode);
						communicateMessage(feedbackHandler.registeredGrade(spnr, grade, ccode));
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
					}
				}
			}
		});

		JButton btn_stud_regOnCourse_search = new JButton("Search");
		btn_stud_regOnCourse_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearFeedback();
				clearTable(table_stud_regOnCourse_courseList);
				String spnr = textField_stud_regOnCourse_pnr.getText().trim();

				if (spnr.isEmpty()) {
					communicateMessage(feedbackHandler.noInputPnr());
				} else {
					String tableName = table_stud_regOnCourse_courseList.getName();
					ArrayList<String> values = new ArrayList<String>();
					values.add(spnr);
					values.add(spnr);
					try {
						if (controllerLu.studentExist(spnr)) {
							table_stud_regOnCourse_courseList.setModel(controllerLu.getTableAll(values, tableName));
							communicateMessage(feedbackHandler.possibleCoursesFound(spnr));
						} else {
							clearTable(table_stud_regOnCourse_courseList);
							communicateMessage(feedbackHandler.noStudentFound(spnr));
						}

					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
					}
				}
			}
		});
		btn_stud_regOnCourse_search.setBounds(301, 314, 108, 23);
		panel_student.add(btn_stud_regOnCourse_search);

		JButton btn_stud_regOnCourse_register = new JButton("Register");
		btn_stud_regOnCourse_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearFeedback();

				String spnr = textField_stud_regOnCourse_pnr.getText().trim();
				if (spnr.isEmpty()) {
					communicateMessage(feedbackHandler.insufficientInput());
				} else {
					try {
						int selectedRow = table_stud_regOnCourse_courseList.getSelectedRow();
						String ccode = (String) table_stud_regOnCourse_courseList.getValueAt(selectedRow, 0);
						String semester = UtilView.getCurrentSemester();
						int coursePoints = (int) table_stud_regOnCourse_courseList.getValueAt(selectedRow, 2);
						int currentPoints = 0;

						if (controllerLu.studentExist(spnr)) {
							currentPoints = controllerLu.currentPoints(spnr);
						} else {
							communicateMessage(feedbackHandler.noStudentFound(spnr));
						}
						if (currentPoints + coursePoints >= 45) {
							communicateMessage(feedbackHandler.pointsExceeded(spnr, currentPoints));
						} else {
							Studying s = new Studying(spnr, ccode, semester);
							controllerLu.registerOnCourse(s);
							communicateMessage(feedbackHandler.studentRegCourse(spnr, ccode));
						}

					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
					}
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
				clearTable(table_stud_foundStud);
				clearTable(table_stud_currentCourses);
				clearTable(table_stud_finishedCourses);
				String spnr = textField_stud_findStudentAll_pnr.getText().trim();

				if (spnr.isEmpty()) {
					communicateMessage(feedbackHandler.noInputPnr());

				} else {
					ArrayList<String> values = new ArrayList<String>();
					values.add(spnr);
					String tableName;

					try {
						tableName = table_stud_finishedCourses.getName();
						dtm_stud_finishedCourses = controllerLu.getTableAll(values, tableName);
						table_stud_finishedCourses.setModel(dtm_stud_finishedCourses);
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
					}
					try {
						tableName = table_stud_currentCourses.getName();
						dtm_stud_currentCourses = controllerLu.getTableAll(values, tableName);
						table_stud_currentCourses.setModel(dtm_stud_currentCourses);
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
					}
					try {
						tableName = table_stud_foundStud.getName();
						dtm_stud_student = controllerLu.getTableAll(values, tableName);
						table_stud_foundStud.setModel(dtm_stud_student);
						communicateMessage(feedbackHandler.studentFound());

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

		JButton btn_stud_findStudentAll_delete = new JButton("Remove");
		btn_stud_findStudentAll_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int selectedRowCurrent = table_stud_currentCourses.getSelectedRow();
					String spnr = (String) table_stud_foundStud.getValueAt(0, 0);
					String ccode = (String) table_stud_currentCourses.getValueAt(selectedRowCurrent, 0);
					controllerLu.deleteStudying(spnr, ccode);
					dtm_stud_currentCourses.removeRow(selectedRowCurrent);
					communicateMessage(feedbackHandler.studentRemovedStudying(spnr, ccode));
				} catch (Exception e) {
					communicateMessage(exceptionHandler.handleException(e));
				}

			}

		});

		btn_stud_findStudentAll_delete.setBounds(730, 558, 100, 23);
		panel_student.add(btn_stud_findStudentAll_delete);

		JSeparator separator_stud_vertical = new JSeparator();
		separator_stud_vertical.setOrientation(SwingConstants.VERTICAL);
		separator_stud_vertical.setBounds(438, 11, 2, 582);
		panel_student.add(separator_stud_vertical);

		JSeparator separator_stud_regGrade = new JSeparator();
		separator_stud_regGrade.setBounds(472, 549, 358, 14);
		panel_student.add(separator_stud_regGrade);

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

	private void clearTable(JTable table) {
		table.setModel(emptyDefTableModel);
	}
}
