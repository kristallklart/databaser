package view;

import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
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
	private ArrayList<String> values = new ArrayList<String>();
	private JLabel lbl_feedback = new JLabel("");
	private ButtonGroup btngr_course;
	private DefaultTableModel emptyDefTableModel = new DefaultTableModel();
	private DefaultTableModel dtm_stud_finishedCourses = new DefaultTableModel();
	private DefaultTableModel dtm_stud_currentCourses = new DefaultTableModel();
	private DefaultTableModel dtm_stud_student = new DefaultTableModel();
	private JTable table_stud_currentCourses = new JTable();
	private JPanel contentPane;
	private final int BUTTON_WIDTH = 108;
	private final int BUTTON_HEIGHT = 23;
	private final int TEXTFIELD_WIDTH = 159;
	private final int TEXTFIELD_HEIGHT = 25;
	private final int LABEL_WIDTH = 108;
	private final int LABEL_HEIGHT = 23;
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
	private JRadioButton rdbtn_course_showNotFinished;

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
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
				}
			}
		} catch (Exception e) {
			communicateMessage(exceptionHandler.handleException(e));
			e.printStackTrace();
		}

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

		lbl_feedback.setBounds(19, 635, 1242, 20);
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
		lbl_course_ccode.setBounds(35, 35, LABEL_WIDTH, LABEL_HEIGHT);
		panel_course.add(lbl_course_ccode);

		JLabel lbl_course_name = new JLabel("Course name:");
		lbl_course_name.setBounds(35, 69, LABEL_WIDTH, LABEL_HEIGHT);
		panel_course.add(lbl_course_name);

		JLabel lbl_course_points = new JLabel("Points:");
		lbl_course_points.setBounds(35, 103, LABEL_WIDTH, LABEL_HEIGHT);
		panel_course.add(lbl_course_points);

		JLabel lbl_course_showGradeA_header = new JLabel("% of Students with grade A:");
		lbl_course_showGradeA_header.setBounds(607, 567, 167, 14);
		panel_course.add(lbl_course_showGradeA_header);

		JLabel lbl_course_showGradeA_result = new JLabel("");
		lbl_course_showGradeA_result.setBounds(784, 567, 46, 14);
		panel_course.add(lbl_course_showGradeA_result);

		JLabel lbl_course_selectCourse_header = new JLabel("Select course:");
		lbl_course_selectCourse_header.setBounds(629, 67, 115, 14);
		panel_course.add(lbl_course_selectCourse_header);

		textField_course_courseCode = new JTextField();
		textField_course_courseCode.setColumns(10);
		textField_course_courseCode.setBounds(131, 34, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_course.add(textField_course_courseCode);
		coursePanelFields.add(textField_course_courseCode);

		textField_course_cname = new JTextField();
		textField_course_cname.setColumns(10);
		textField_course_cname.setBounds(131, 68, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_course.add(textField_course_cname);
		coursePanelFields.add(textField_course_cname);

		textField_course_points = new JTextField();
		textField_course_points.setColumns(10);
		textField_course_points.setBounds(131, 103, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_course.add(textField_course_points);
		coursePanelFields.add(textField_course_points);

		textField_course_enrolled_ccode = new JTextField();
		textField_course_enrolled_ccode.setColumns(10);
		textField_course_enrolled_ccode.setBounds(803, 62, 159, 25);
		panel_course.add(textField_course_enrolled_ccode);

		JScrollPane scrollPane_course = new JScrollPane();
		scrollPane_course.setBounds(583, 262, 563, 294);
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
		ButtonGroup btngr_course = new ButtonGroup();
		JRadioButton rdbtn_course_showAll = new JRadioButton("Show all courses");
		rdbtn_course_showAll.setBounds(629, 181, 128, 23);
		panel_course.add(rdbtn_course_showAll);
		btngr_course.add(rdbtn_course_showAll);

		JRadioButton rdbtn_course_highestThrough = new JRadioButton(
				"Show the top 5 courses with the highest throughoutput", false);
		rdbtn_course_highestThrough.setBounds(629, 207, 360, 23);
		panel_course.add(rdbtn_course_highestThrough);
		btngr_course.add(rdbtn_course_highestThrough);

		JRadioButton rdbtn_course_showNotFinished = new JRadioButton(
				"Only show students who hasn't finished the course", false);
		rdbtn_course_showNotFinished.setBounds(629, 95, 360, 23);
		panel_course.add(rdbtn_course_showNotFinished);
		btngr_course.add(rdbtn_course_showNotFinished);
		btn_course_addCourse_clear.setBounds(64, 137, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_addCourse_clear);

		JButton btn_course_deleteAdd_delete = new JButton("Delete");
		btn_course_deleteAdd_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String ccode = textField_course_courseCode.getText().trim();
				if (ccode.isEmpty()) {
					communicateMessage(feedbackHandler.noCcodeDelete());
				} else {
					try {
						controllerLu.deleteCourse(ccode);
						communicateMessage(feedbackHandler.courseDeleted(ccode));
						UtilView.clearFields(coursePanelFields);
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
					}
				}
			}
		});
		btn_course_deleteAdd_delete.setBounds(300, 137, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_deleteAdd_delete);

		JButton btn_course_deleteAdd_add = new JButton("Add Course");
		btn_course_deleteAdd_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String ccode = textField_course_courseCode.getText().trim();
				String cname = textField_course_cname.getText().trim();

				if (ccode.isEmpty() || cname.isEmpty()) {
					communicateMessage(feedbackHandler.insufficientInput());
				} else {
					try {
						int cpoints = Integer.parseInt(textField_course_points.getText().trim());
						Course c = new Course(ccode, cname, cpoints);
						controllerLu.addCourse(c);
						UtilView.clearFields(coursePanelFields);
						communicateMessage(feedbackHandler.courseAdded(ccode));
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
					}
				}
			}
		});
		btn_course_deleteAdd_add.setBounds(182, 137, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_deleteAdd_add);

		JButton btn_course_deleteAdd_search = new JButton("Search");
		btn_course_deleteAdd_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String ccode = textField_course_courseCode.getText().trim();
				if (ccode.isEmpty()) {
					communicateMessage(feedbackHandler.noInputCcode());
				} else {
					try {
						Course c = controllerLu.getCourse(ccode);
						textField_course_cname.setText(c.getCname());
						textField_course_points.setText(String.valueOf(c.getCpoint()));
						communicateMessage(feedbackHandler.courseFound());
					} catch (Exception e) {
						e.printStackTrace();
						communicateMessage(exceptionHandler.handleException(e));
					}
				}
			}
		});
		btn_course_deleteAdd_search.setBounds(300, 35, 108, 23);
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
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
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
							communicateMessage(exceptionHandler.handleException(e));
							e.printStackTrace();
						}
					}
				}
			}

		});
		btn_course_courseInfo_showResult.setBounds(1007, 207, 108, 23);
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
						e.printStackTrace();
					}
				}

			}
		});
		btn_course_enrolled_showResult.setBounds(1007, 62, 108, 23);
		panel_course.add(btn_course_enrolled_showResult);

		JPanel panel_course_addBorder = new JPanel();
		panel_course_addBorder.setBorder(
				new TitledBorder(null, "Add/Delete Course", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_course_addBorder.setBounds(10, 11, 491, 192);
		panel_course.add(panel_course_addBorder);

		JPanel panel_course_resultsBorder = new JPanel();
		panel_course_resultsBorder.setBorder(
				new TitledBorder(null, "Course Results", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_course_resultsBorder.setBounds(583, 38, 563, 115);
		panel_course.add(panel_course_resultsBorder);

		JPanel panel_course_informationBorder = new JPanel();
		panel_course_informationBorder.setBorder(
				new TitledBorder(null, "Course Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_course_informationBorder.setBounds(583, 164, 563, 87);
		panel_course.add(panel_course_informationBorder);

		JPanel panel_course_CourseInfBorder = new JPanel();
		panel_course_CourseInfBorder.setBorder(new TitledBorder(null, "Search Course Information", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_course_CourseInfBorder.setBounds(566, 11, 601, 586);
		panel_course.add(panel_course_CourseInfBorder);

		// ***********************************
		// ***********STUDENT TAB*************
		// ***********************************

		JButton btn_stud_deleteAdd_search = new JButton("Search");
		btn_stud_deleteAdd_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
						e.printStackTrace();
					}
				}
			}
		});

		JLabel lbl_stud_deleteAdd_pnr = new JLabel("Personal number");
		lbl_stud_deleteAdd_pnr.setBounds(28, 48, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_deleteAdd_pnr);

		JLabel lbl_stud_deleteAdd_name = new JLabel("Name:");
		lbl_stud_deleteAdd_name.setBounds(28, 84, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_deleteAdd_name);

		JLabel lbl_stud_deleteAdd_address = new JLabel("Address:");
		lbl_stud_deleteAdd_address.setBounds(28, 120, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_deleteAdd_address);

		JLabel lbl_stud_regOnCourse_pnr = new JLabel("Personal number:");
		lbl_stud_regOnCourse_pnr.setBounds(28, 308, 108, 23);
		panel_student.add(lbl_stud_regOnCourse_pnr);

		JLabel lbl_stud_regOnCourse_selectFromList = new JLabel("Select course from the list:");
		lbl_stud_regOnCourse_selectFromList.setBounds(28, 343, 216, 14);
		panel_student.add(lbl_stud_regOnCourse_selectFromList);

		JLabel lbl_stud_findStudentAll_pnr = new JLabel("Personal number:");
		lbl_stud_findStudentAll_pnr.setBounds(472, 47, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_findStudentAll_pnr);

		JLabel lbl_stud_removeStudentCourse_header = new JLabel("Remove student from selected course");
		lbl_stud_removeStudentCourse_header.setBounds(482, 556, 228, 14);
		panel_student.add(lbl_stud_removeStudentCourse_header);

		JLabel lbl_stud_regGrade_header = new JLabel("Register grade for selected course");
		lbl_stud_regGrade_header.setBounds(472, 468, 248, 23);
		panel_student.add(lbl_stud_regGrade_header);

		JLabel lbl_stud_finishedCourses_header = new JLabel("Finished Courses:");
		lbl_stud_finishedCourses_header.setBounds(856, 213, 108, 14);
		panel_student.add(lbl_stud_finishedCourses_header);

		JLabel lbl_stud_currentCourses_header = new JLabel("Current Courses: ");
		lbl_stud_currentCourses_header.setBounds(472, 213, 108, 14);
		panel_student.add(lbl_stud_currentCourses_header);

		textField_stud_findStudentAll_pnr = new JTextField();
		textField_stud_findStudentAll_pnr.setBounds(592, 46, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_findStudentAll_pnr);
		textField_stud_findStudentAll_pnr.setColumns(10);

		textField_stud_deleteAdd_pnr = new JTextField();
		textField_stud_deleteAdd_pnr.setBounds(148, 47, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_deleteAdd_pnr);
		textField_stud_deleteAdd_pnr.setColumns(10);
		studPanelFields.add(textField_stud_deleteAdd_pnr);

		textField_stud_deleteAdd_name = new JTextField();
		textField_stud_deleteAdd_name.setBounds(148, 83, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		studPanelFields.add(textField_stud_deleteAdd_name);
		panel_student.add(textField_stud_deleteAdd_name);
		textField_stud_deleteAdd_name.setColumns(10);

		textField_stud_deleteAdd_address = new JTextField();
		textField_stud_deleteAdd_address.setBounds(148, 119, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		studPanelFields.add(textField_stud_deleteAdd_address);
		panel_student.add(textField_stud_deleteAdd_address);
		textField_stud_deleteAdd_address.setColumns(10);

		textField_stud_regOnCourse_pnr = new JTextField();
		textField_stud_regOnCourse_pnr.setColumns(10);
		textField_stud_regOnCourse_pnr.setBounds(148, 307, 159, 25);
		panel_student.add(textField_stud_regOnCourse_pnr);

		JScrollPane scrollPane_stud_foundStudent = new JScrollPane();
		scrollPane_stud_foundStudent.setBounds(472, 109, 391, 70);
		table_stud_foundStud = new JTable();
		table_stud_foundStud.setName("table_stud_foundStud");
		scrollPane_stud_foundStudent.setViewportView(table_stud_foundStud);
		panel_student.add(scrollPane_stud_foundStudent);

		JScrollPane scrollPane_stud_currentCourses = new JScrollPane();
		scrollPane_stud_currentCourses.setBounds(472, 238, 358, 219);
		table_stud_currentCourses = new JTable();
		table_stud_currentCourses.setName("table_stud_currentCourses");
		scrollPane_stud_currentCourses.setViewportView(table_stud_currentCourses);
		panel_student.add(scrollPane_stud_currentCourses);

		JScrollPane scrollPane_stud_finishedCourses = new JScrollPane();
		scrollPane_stud_finishedCourses.setBounds(856, 238, 367, 219);
		panel_student.add(scrollPane_stud_finishedCourses);
		table_stud_finishedCourses = new JTable();
		table_stud_finishedCourses.setName("table_stud_finishedCourses");
		scrollPane_stud_finishedCourses.setViewportView(table_stud_finishedCourses);

		JScrollPane scrollPane_stud_regOnCourse_courseList = new JScrollPane();
		scrollPane_stud_regOnCourse_courseList.setBounds(28, 368, 397, 173);
		panel_student.add(scrollPane_stud_regOnCourse_courseList);
		table_stud_regOnCourse_courseList = new JTable();
		table_stud_regOnCourse_courseList.setName("table_stud_regOnCourse_courseList");
		scrollPane_stud_regOnCourse_courseList.setViewportView(table_stud_regOnCourse_courseList);

		JComboBox<String> comboBox_stud_grade_1 = new JComboBox<String>();
		comboBox_stud_grade_1.setBounds(472, 496, 146, 25);
		panel_student.add(comboBox_stud_grade_1);
		comboBox_stud_grade_1.addItem("Select Grade...");
		comboBox_stud_grade_1.addItem("A");
		comboBox_stud_grade_1.addItem("B");
		comboBox_stud_grade_1.addItem("C");
		comboBox_stud_grade_1.addItem("D");
		comboBox_stud_grade_1.addItem("E");
		comboBox_stud_grade_1.addItem("U");

		JComboBox<String> comboBox_studentRegisterSemester = new JComboBox<String>();
		comboBox_studentRegisterSemester.setBounds(28, 555, 166, 23);
		panel_student.add(comboBox_studentRegisterSemester);
		comboBox_studentRegisterSemester.addItem("Select Semester...");
		comboBox_studentRegisterSemester.addItem("HT16");
		comboBox_studentRegisterSemester.addItem("VT17");
		comboBox_studentRegisterSemester.addItem("HT17");
		comboBox_studentRegisterSemester.addItem("VT18");

		btn_stud_deleteAdd_search.setBounds(317, 48, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_deleteAdd_search);

		JButton btn_stud_deleteAdd_clear = new JButton("Clear");
		btn_stud_deleteAdd_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UtilView.clearFields(studPanelFields);
			}
		});
		btn_stud_deleteAdd_clear.setBounds(57, 156, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_deleteAdd_clear);

		JButton btn_stud_deleteAdd_delete = new JButton("Delete");
		btn_stud_deleteAdd_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
						e.printStackTrace();
					}
				}
			}
		});
		btn_stud_deleteAdd_delete.setBounds(317, 156, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_deleteAdd_delete);

		JButton btn_stud_deleteAdd_add = new JButton("Add new student");
		btn_stud_deleteAdd_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String spnr = textField_stud_deleteAdd_pnr.getText().trim();
				String name = textField_stud_deleteAdd_name.getText().trim();
				String address = textField_stud_deleteAdd_address.getText().trim();

				if (spnr.isEmpty() || name.isEmpty() || address.isEmpty()) {
					communicateMessage(feedbackHandler.insufficientInput());
				} else {
					Student s = new Student(spnr, name, address);
					try {
						controllerLu.addStudent(s);
						UtilView.clearFields(studPanelFields);
						communicateMessage(feedbackHandler.studentAdded(spnr));
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
					}
				}

			}
		});
		btn_stud_deleteAdd_add.setBounds(175, 156, 132, 23);
		panel_student.add(btn_stud_deleteAdd_add);

		JButton btn_stud_findStudentAll_register = new JButton("Register Grade");
		btn_stud_findStudentAll_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String spnr = textField_stud_findStudentAll_pnr.getText().trim();
				int selectedRow = table_stud_currentCourses.getSelectedRow();
				if (spnr.isEmpty() || selectedRow < 0) {
					communicateMessage(feedbackHandler.insufficientInput());
				} else {
					try {
						String grade = comboBox_stud_grade_1.getSelectedItem().toString();
						String ccode = (String) table_stud_currentCourses.getValueAt(selectedRow, 0);
						String semester = (String) table_stud_currentCourses.getValueAt(selectedRow, 1);
						Studied s = new Studied(spnr, semester, grade, ccode);
						values.clear();
						values.add(spnr);
						controllerLu.registerGrade(s);
						controllerLu.deleteStudying(spnr, ccode);

						table_stud_currentCourses
								.setModel(controllerLu.updateTable(values, table_stud_currentCourses.getName()));

						table_stud_finishedCourses
								.setModel(controllerLu.updateTable(values, table_stud_finishedCourses.getName()));

						communicateMessage(feedbackHandler.registeredGrade(spnr, grade, ccode));
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
					}
				}
			}
		});

		JButton btn_stud_regOnCourse_search = new JButton("Search");
		btn_stud_regOnCourse_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearTable(table_stud_regOnCourse_courseList);
				String spnr = textField_stud_regOnCourse_pnr.getText().trim();

				if (spnr.isEmpty()) {
					communicateMessage(feedbackHandler.noInputPnr());
				} else {
					String tableName = table_stud_regOnCourse_courseList.getName();
					values.clear();
					values.add(spnr);
					values.add(spnr);
					try {
						if (controllerLu.studentExist(spnr)) {
							table_stud_regOnCourse_courseList.setModel(controllerLu.getTable(values, tableName));
							communicateMessage(feedbackHandler.availableCoursesFound(spnr));
						} else {
							clearTable(table_stud_regOnCourse_courseList);
							communicateMessage(feedbackHandler.noStudentFound(spnr));
						}

					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
					}
				}
			}
		});
		btn_stud_regOnCourse_search.setBounds(317, 308, 108, 23);
		panel_student.add(btn_stud_regOnCourse_search);

		JButton btn_stud_regOnCourse_register = new JButton("Register");
		btn_stud_regOnCourse_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String spnr = textField_stud_regOnCourse_pnr.getText().trim();
				int selectedTableRow = table_stud_regOnCourse_courseList.getSelectedRow();
				int selectedBoxRow = comboBox_studentRegisterSemester.getSelectedIndex();
				if (spnr.isEmpty() || selectedBoxRow == 0 || selectedTableRow < 0) {
					communicateMessage(feedbackHandler.insufficientInput());
				} else {
					try {
						String ccode = (String) table_stud_regOnCourse_courseList.getValueAt(selectedTableRow, 0);
						String semester = (String) comboBox_studentRegisterSemester.getSelectedItem();
						int coursePoints = (int) table_stud_regOnCourse_courseList.getValueAt(selectedTableRow, 2);
						int currentPoints = 0;

						if (controllerLu.studentExist(spnr)) {
							currentPoints = controllerLu.currentPoints(spnr);

							if (currentPoints + coursePoints > 45) {
								communicateMessage(feedbackHandler.pointsExceeded(spnr, currentPoints));
							} else {
								Studying s = new Studying(spnr, ccode, semester);
								controllerLu.registerOnCourse(s);
								values.clear();
								values.add(spnr);
								values.add(spnr);

								table_stud_regOnCourse_courseList.setModel(
										controllerLu.updateTable(values, table_stud_regOnCourse_courseList.getName()));

								values.remove(values.size() - 1);

								table_stud_foundStud
										.setModel(controllerLu.updateTable(values, table_stud_foundStud.getName()));

								textField_stud_findStudentAll_pnr.setText(spnr);

								table_stud_currentCourses.setModel(
										controllerLu.updateTable(values, table_stud_currentCourses.getName()));

								table_stud_finishedCourses.setModel(
										controllerLu.updateTable(values, table_stud_finishedCourses.getName()));

								communicateMessage(feedbackHandler.studentRegCourse(spnr, ccode));
							}

						} else {
							communicateMessage(feedbackHandler.noStudentFound(spnr));
						}
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
					}
				}
			}
		});
		btn_stud_regOnCourse_register.setBounds(311, 555, 114, 23);
		panel_student.add(btn_stud_regOnCourse_register);

		JButton btn_stud_findStudentAll_search = new JButton("Search");
		btn_stud_findStudentAll_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearTable(table_stud_foundStud);
				clearTable(table_stud_currentCourses);
				clearTable(table_stud_finishedCourses);
				String spnr = textField_stud_findStudentAll_pnr.getText().trim();

				if (spnr.isEmpty()) {
					communicateMessage(feedbackHandler.noInputPnr());

				} else {
					values.clear();
					values.add(spnr);
					String tableName;

					try {
						tableName = table_stud_finishedCourses.getName();
						dtm_stud_finishedCourses = controllerLu.getTable(values, tableName);
						table_stud_finishedCourses.setModel(dtm_stud_finishedCourses);
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
					}
					try {
						tableName = table_stud_currentCourses.getName();
						dtm_stud_currentCourses = controllerLu.getTable(values, tableName);
						table_stud_currentCourses.setModel(dtm_stud_currentCourses);
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
					}
					try {
						tableName = table_stud_foundStud.getName();
						dtm_stud_student = controllerLu.getTable(values, tableName);
						table_stud_foundStud.setModel(dtm_stud_student);
						communicateMessage(feedbackHandler.studentFound());

					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
					}
				}
			}
		});
		btn_stud_findStudentAll_search.setBounds(763, 47, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_findStudentAll_search);
		btn_stud_findStudentAll_register.setBounds(628, 497, 139, 23);
		panel_student.add(btn_stud_findStudentAll_register);

		JButton btn_stud_findStudentAll_delete = new JButton("Remove");
		btn_stud_findStudentAll_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedRowCurrent = table_stud_currentCourses.getSelectedRow();
				if (selectedRowCurrent < 0) {
					communicateMessage(feedbackHandler.insufficientInput());
				} else {
					try {
						String spnr = (String) table_stud_foundStud.getValueAt(0, 0);
						String ccode = (String) table_stud_currentCourses.getValueAt(selectedRowCurrent, 0);
						controllerLu.deleteStudying(spnr, ccode);
						dtm_stud_currentCourses.removeRow(selectedRowCurrent);
						communicateMessage(feedbackHandler.studentRemovedStudying(spnr, ccode));
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
					}
				}
			}
		});

		btn_stud_findStudentAll_delete.setBounds(730, 552, 100, 23);
		panel_student.add(btn_stud_findStudentAll_delete);

		JSeparator separator_stud_regGrade = new JSeparator();
		separator_stud_regGrade.setBounds(472, 532, 358, 14);
		panel_student.add(separator_stud_regGrade);

		JPanel panel_student_addBorder = new JPanel();
		panel_student_addBorder.setBorder(
				new TitledBorder(null, "Add/Delete Student", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_student_addBorder.setBounds(12, 11, 435, 186);
		panel_student.add(panel_student_addBorder);

		JPanel panel_student_registerBorder = new JPanel();
		panel_student_registerBorder.setBorder(new TitledBorder(null, "Register Student to Course",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_student_registerBorder.setBounds(12, 273, 435, 324);
		panel_student.add(panel_student_registerBorder);

		JPanel panel_student_informtionBoder = new JPanel();
		panel_student_informtionBoder.setBorder(new TitledBorder(null, "Search Student Information",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_student_informtionBoder.setBounds(457, 11, 790, 586);
		panel_student.add(panel_student_informtionBoder);

		// ***************************************
		// ***********CRONUS ACCESS TAB***********
		// ***************************************

		table_caccess = new JTable();
		JPanel panel_caccess = new JPanel();
		tabbedPane.addTab("Cronus Access", panel_caccess);
		panel_caccess.setLayout(null);

		JScrollPane scrollPane_caccess = new JScrollPane();
		scrollPane_caccess.setBounds(622, 32, 544, 395);
		panel_caccess.add(scrollPane_caccess);

		table_caccess = new JTable();
		scrollPane_caccess.setViewportView(table_caccess);

		JPanel panel_CronusAccess_showTables = new JPanel();
		panel_CronusAccess_showTables.setBorder(new TitledBorder(null, "Show employee and related tables",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_CronusAccess_showTables.setBounds(10, 11, 496, 168);
		panel_caccess.add(panel_CronusAccess_showTables);
		panel_CronusAccess_showTables.setLayout(null);

		JLabel lbl_caccess_selectOption = new JLabel("Select table");
		lbl_caccess_selectOption.setBounds(23, 45, 87, 23);
		panel_CronusAccess_showTables.add(lbl_caccess_selectOption);

		JComboBox<String> comboBox_caccessTables = new JComboBox(controllerCronus.getCronusQueryNamesTables());
		comboBox_caccessTables.setBounds(120, 44, 297, 25);
		panel_CronusAccess_showTables.add(comboBox_caccessTables);
		comboBox_caccessTables.setName("comboBox_caccessTables");

		JButton btn_caccess__showTables = new JButton("Show");
		btn_caccess__showTables.setBounds(309, 102, 108, 23);
		panel_CronusAccess_showTables.add(btn_caccess__showTables);

		JPanel panel_CronusAccess_showMetadata = new JPanel();
		panel_CronusAccess_showMetadata.setBorder(
				new TitledBorder(null, "Show meta data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_CronusAccess_showMetadata.setBounds(10, 259, 496, 168);
		panel_caccess.add(panel_CronusAccess_showMetadata);
		panel_CronusAccess_showMetadata.setLayout(null);

		JComboBox<String> comboBox_caccessMeta = new JComboBox(controllerCronus.getCronusQueryNamesMetaData());

		comboBox_caccessMeta.setBounds(124, 46, 297, 25);
		panel_CronusAccess_showMetadata.add(comboBox_caccessMeta);
		comboBox_caccessMeta.setName("comboBox_caccessMeta");

		JButton btn_caccess_showMetadata = new JButton("Show");
		btn_caccess_showMetadata.setBounds(313, 98, 108, 23);
		panel_CronusAccess_showMetadata.add(btn_caccess_showMetadata);

		JLabel lblNewLabel = new JLabel("Select query");
		lblNewLabel.setBounds(23, 49, 75, 19);
		panel_CronusAccess_showMetadata.add(lblNewLabel);

		JSeparator separator_cronusAccess = new JSeparator();
		separator_cronusAccess.setBounds(10, 212, 496, 2);
		panel_caccess.add(separator_cronusAccess);

		btn_caccess_showMetadata.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedCronusIndex = comboBox_caccessMeta.getSelectedIndex();
				String nameCombobox = comboBox_caccessMeta.getName();
				if (selectedCronusIndex != 0) {
					try {
						table_caccess.setModel(controllerCronus.getTableModel(nameCombobox, selectedCronusIndex));

					} catch (SQLException e) {
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
					}
				} else {
					communicateMessage(feedbackHandler.notFullChoiceCronusAccess());
				}
			}
		});

		btn_caccess__showTables.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedCronusIndex = comboBox_caccessTables.getSelectedIndex();
				String nameCombobox = comboBox_caccessTables.getName();
				if (selectedCronusIndex != 0) {
					try {
						table_caccess.setModel(controllerCronus.getTableModel(nameCombobox, selectedCronusIndex));
					} catch (SQLException e) {
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
					}
				} else {
					communicateMessage(feedbackHandler.notFullChoiceCronusAccess());
				}
			}
		});

		// ********************************
		// ********** OPEN FILES **********
		// ********************************

		JPanel panel_openFiles = new JPanel();
		tabbedPane.addTab("Open files", null, panel_openFiles, null);
		panel_openFiles.setLayout(null);

		String[] programToUseIndexAccess = { "Select...", "Access", "Excel", "SQL Management Studio" };

		JSeparator separator_open_files = new JSeparator();
		separator_open_files.setBounds(10, 261, 500, 2);
		panel_openFiles.add(separator_open_files);

		String[] programToUseIndexForm = { "Select...", "Access", "Excel", "Word" };

		String[] queryToUseIndexForm = { "Select...", "Employee", "Customer" };

		JPanel panel_open_queryFiles = new JPanel();
		panel_open_queryFiles.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Open query files",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_open_queryFiles.setBounds(10, 11, 500, 208);
		panel_openFiles.add(panel_open_queryFiles);
		panel_open_queryFiles.setLayout(null);

		JLabel lblSelectOption = new JLabel("Type of file:");
		lblSelectOption.setBounds(34, 43, 112, 39);
		panel_open_queryFiles.add(lblSelectOption);

		JComboBox comboBox_access_excel = new JComboBox(programToUseIndexAccess);
		comboBox_access_excel.setBounds(156, 49, 300, 26);
		panel_open_queryFiles.add(comboBox_access_excel);
		comboBox_access_excel.setName("comboBox_access_excel");

		JComboBox comboBox_cdatabase_query = new JComboBox(controllerCronus.getCronusFileNameToOpen());
		comboBox_cdatabase_query.setBounds(156, 99, 300, 26);
		panel_open_queryFiles.add(comboBox_cdatabase_query);
		comboBox_cdatabase_query.setName("comboBox_cdatabase_query");

		JLabel lblSelectQuery = new JLabel("Specified query:");
		lblSelectQuery.setBounds(34, 93, 112, 39);
		panel_open_queryFiles.add(lblSelectQuery);

		JButton btn_open_queryFiles = new JButton("Open");
		btn_open_queryFiles.setBounds(348, 154, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_open_queryFiles.add(btn_open_queryFiles);
		btn_open_queryFiles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_access_excel.getSelectedIndex() != 0 && comboBox_cdatabase_query.getSelectedIndex() != 0) {
					try {
						controllerCronus.openCronusFile(comboBox_access_excel.getName(),
								comboBox_access_excel.getSelectedIndex(), comboBox_cdatabase_query.getSelectedIndex());
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
					}
				} else {
					communicateMessage(feedbackHandler.notFullChoiceCronusFile());
				}
			}
		});

		JPanel panel_open_forms = new JPanel();
		panel_open_forms.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Open form files",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_open_forms.setBounds(10, 310, 500, 208);
		panel_openFiles.add(panel_open_forms);
		panel_open_forms.setLayout(null);

		JLabel lbl_oform_selectOption = new JLabel("Type of file:");
		lbl_oform_selectOption.setBounds(34, 35, 112, 39);
		panel_open_forms.add(lbl_oform_selectOption);

		JComboBox comboBox_oform_selectProgram = new JComboBox(programToUseIndexForm);
		comboBox_oform_selectProgram.setBounds(156, 41, 298, 26);
		panel_open_forms.add(comboBox_oform_selectProgram);
		comboBox_oform_selectProgram.setName("comboBox_oform_selectProgram");

		JLabel lbl_oform_select = new JLabel("Specified form:");
		lbl_oform_select.setBounds(34, 85, 112, 39);
		panel_open_forms.add(lbl_oform_select);

		JComboBox comboBox_oform_selectQuery = new JComboBox(queryToUseIndexForm);
		comboBox_oform_selectQuery.setBounds(156, 91, 298, 26);
		panel_open_forms.add(comboBox_oform_selectQuery);
		comboBox_oform_selectQuery.setName("comboBox_oform_selectQuery");

		JButton btn_open_forms = new JButton("Open");
		btn_open_forms.setBounds(346, 151, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_open_forms.add(btn_open_forms);
		btn_open_forms.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_oform_selectProgram.getSelectedIndex() != 0
						&& comboBox_oform_selectQuery.getSelectedIndex() != 0) {
					try {
						controllerCronus.openCronusFile(comboBox_oform_selectProgram.getName(),
								comboBox_oform_selectProgram.getSelectedIndex(),
								comboBox_oform_selectQuery.getSelectedIndex());
					} catch (Exception e) {
						communicateMessage(exceptionHandler.handleException(e));
						e.printStackTrace();
					}
				} else {
					communicateMessage(feedbackHandler.notFullChoiceCronusFile());
				}
			}
		});

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
