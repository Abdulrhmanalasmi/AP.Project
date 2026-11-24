package car;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Car extends JFrame {
    Connection con;
    Statement st;
    ResultSet rs;

    // المنيو بار
    JMenuBar bar = new JMenuBar();
    JMenu mSettings = new JMenu("Settings");
    JMenu mColor = new JMenu("Page Color");
    JMenuItem itBlack = new JMenuItem("Black");
    JMenuItem itWhite = new JMenuItem("White");

    JMenu mFont = new JMenu("Font Style");
    JMenuItem itBold = new JMenuItem("Bold");
    JMenuItem itItalic = new JMenuItem("Italic");

    JMenu mCar = new JMenu("Car");
    JMenuItem itView = new JMenuItem("View");
    JMenuItem itInsert = new JMenuItem("Insert");
    JMenuItem itUpdate = new JMenuItem("Update");

    JMenu mGame = new JMenu("Game");
    JMenuItem itPlay = new JMenuItem("Play");

    // 1) شاشة تسجيل الدخول
    JPanel pLogin = new JPanel();
    Icon pic = new ImageIcon(getClass().getResource("p2.png"));
    JLabel L1 = new JLabel(pic);
    JLabel L2 = new JLabel("Enter User Id :");
    JLabel L3 = new JLabel("Enter Password :");
    JTextField t1 = new JTextField();
    JPasswordField pw = new JPasswordField();
    JCheckBox ch = new JCheckBox("Show password");
    JButton b1 = new JButton("Login");
    JButton b2 = new JButton("Cancel");

    // 2) شاشة الترحيب بعد الدخول
    JPanel pWelcome = new JPanel();
    JLabel lWelcome = new JLabel("select view to see cars");

    // 3) شاشة عرض السيارات View
    JPanel pView = new JPanel();
    JButton bBack = new JButton("Back");
    JButton bNext = new JButton("Next");
    JLabel lPic = new JLabel();
    JLabel lName = new JLabel("Car Name : ");
    JLabel lModel = new JLabel("Car Model : ");
    JLabel lPrice = new JLabel("Car Price : ");

    // 4) شاشة الإضافة Insert
    JPanel pInsert = new JPanel();
    JLabel lIn1 = new JLabel("Car Name :");
    JLabel lIn2 = new JLabel("Car Model :");
    JLabel lIn3 = new JLabel("Car Price :");
    JLabel lIn4 = new JLabel("Pic File :");
    JTextField tIn1 = new JTextField(15);
    JTextField tIn2 = new JTextField(15);
    JTextField tIn3 = new JTextField(15);
    JTextField tIn4 = new JTextField(15);
    JButton bInsert = new JButton("Insert Row");

    // 5) شاشة التعديل Update
    JPanel pUpdate = new JPanel();
    JButton bUpBack = new JButton("Back");
    JButton bUpNext = new JButton("Next");
    JLabel lUp1 = new JLabel("Car Name :");
    JLabel lUp2 = new JLabel("Car Model :");
    JLabel lUp3 = new JLabel("Car Price :");
    JLabel lUp4 = new JLabel("Pic File :");
    JTextField tUp1 = new JTextField(15);
    JTextField tUp2 = new JTextField(15);
    JTextField tUp3 = new JTextField(15);
    JTextField tUp4 = new JTextField(15);
    JButton bUpdate = new JButton("Update Row");

    // 6) شاشة اللعبة Game
    GamePanel pGame = new GamePanel();
    JLabel lGameTitle = new JLabel("Try to stop the time at 10.00");
    JButton bStart = new JButton("Start");
    JButton bStop = new JButton("Stop");
    JButton bReset = new JButton("Reset");

    Font f = new Font("Serif", Font.BOLD, 20);
    Font fBig = new Font("Serif", Font.BOLD, 30);

    public Car() {
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // تجهيز المنيو بار
        mColor.add(itBlack);
        mColor.add(itWhite);
        mFont.add(itBold);
        mFont.add(itItalic);
        mSettings.add(mColor);
        mSettings.add(mFont);

        mCar.add(itView);
        mCar.add(itInsert);
        mCar.add(itUpdate);

        mGame.add(itPlay);

        bar.add(mSettings);
        bar.add(mCar);
        bar.add(mGame);
        setJMenuBar(bar);
        bar.setVisible(false);

        // تجهيز شاشة تسجيل الدخول
        pLogin.setLayout(null);
        pLogin.setBounds(0, 0, 600, 500);
        pLogin.setBackground(Color.WHITE);

        L1.setBounds(150, 20, 300, 200);
        L2.setBounds(100, 245, 150, 25);
        t1.setBounds(250, 245, 200, 25);
        L3.setBounds(100, 285, 150, 25);
        pw.setBounds(250, 285, 200, 25);
        ch.setBounds(460, 285, 130, 25);
        b1.setBounds(180, 345, 100, 30);
        b2.setBounds(310, 345, 100, 30);

        pLogin.add(L1);
        pLogin.add(L2);
        pLogin.add(t1);
        pLogin.add(L3);
        pLogin.add(pw);
        pLogin.add(ch);
        pLogin.add(b1);
        pLogin.add(b2);
        add(pLogin);

        // تجهيز شاشة الترحيب
        pWelcome.setLayout(null);
        pWelcome.setBounds(0, 0, 800, 600);
        pWelcome.setBackground(Color.WHITE);
        lWelcome.setFont(fBig);
        lWelcome.setBounds(235, 230, 400, 50);
        pWelcome.add(lWelcome);
        pWelcome.setVisible(false);
        add(pWelcome);

        // تجهيز شاشة View
        pView.setLayout(null);
        pView.setBounds(0, 0, 800, 600);
        pView.setBackground(Color.WHITE);

        bBack.setBounds(260, 30, 120, 35);
        bNext.setBounds(420, 30, 120, 35);
        lPic.setBounds(250, 85, 300, 240);
        lName.setBounds(250, 345, 400, 35);
        lModel.setBounds(250, 390, 400, 35);
        lPrice.setBounds(250, 435, 400, 35);

        lName.setFont(f);
        lModel.setFont(f);
        lPrice.setFont(f);

        pView.add(bBack);
        pView.add(bNext);
        pView.add(lPic);
        pView.add(lName);
        pView.add(lModel);
        pView.add(lPrice);
        pView.setVisible(false);
        add(pView);

        // تجهيز شاشة Insert
        pInsert.setLayout(null);
        pInsert.setBounds(0, 0, 800, 600);
        pInsert.setBackground(Color.WHITE);

        lIn1.setBounds(180, 100, 150, 30);
        tIn1.setBounds(340, 100, 220, 30);
        lIn2.setBounds(180, 150, 150, 30);
        tIn2.setBounds(340, 150, 220, 30);
        lIn3.setBounds(180, 200, 150, 30);
        tIn3.setBounds(340, 200, 220, 30);
        lIn4.setBounds(180, 250, 150, 30);
        tIn4.setBounds(340, 250, 220, 30);
        bInsert.setBounds(320, 320, 150, 35);

        lIn1.setFont(f);
        lIn2.setFont(f);
        lIn3.setFont(f);
        lIn4.setFont(f);

        pInsert.add(lIn1); pInsert.add(tIn1);
        pInsert.add(lIn2); pInsert.add(tIn2);
        pInsert.add(lIn3); pInsert.add(tIn3);
        pInsert.add(lIn4); pInsert.add(tIn4);
        pInsert.add(bInsert);
        pInsert.setVisible(false);
        add(pInsert);

        // تجهيز شاشة Update
        pUpdate.setLayout(null);
        pUpdate.setBounds(0, 0, 800, 600);
        pUpdate.setBackground(Color.WHITE);

        bUpBack.setBounds(260, 40, 120, 35);
        bUpNext.setBounds(420, 40, 120, 35);
        lUp1.setBounds(180, 110, 150, 30);
        tUp1.setBounds(340, 110, 220, 30);
        lUp2.setBounds(180, 160, 150, 30);
        tUp2.setBounds(340, 160, 220, 30);
        lUp3.setBounds(180, 210, 150, 30);
        tUp3.setBounds(340, 210, 220, 30);
        lUp4.setBounds(180, 260, 150, 30);
        tUp4.setBounds(340, 260, 220, 30);
        bUpdate.setBounds(320, 330, 150, 35);

        lUp1.setFont(f);
        lUp2.setFont(f);
        lUp3.setFont(f);
        lUp4.setFont(f);

        pUpdate.add(bUpBack); pUpdate.add(bUpNext);
        pUpdate.add(lUp1); pUpdate.add(tUp1);
        pUpdate.add(lUp2); pUpdate.add(tUp2);
        pUpdate.add(lUp3); pUpdate.add(tUp3);
        pUpdate.add(lUp4); pUpdate.add(tUp4);
        pUpdate.add(bUpdate);
        pUpdate.setVisible(false);
        add(pUpdate);

        // تجهيز شاشة اللعبة Game
        pGame.setLayout(null);
        pGame.setBounds(0, 0, 800, 600);
        pGame.setBackground(Color.WHITE);

        lGameTitle.setFont(fBig);
        lGameTitle.setBounds(210, 40, 450, 40);
        bStart.setBounds(210, 380, 110, 40);
        bStop.setBounds(345, 380, 110, 40);
        bReset.setBounds(480, 380, 110, 40);

        pGame.add(lGameTitle);
        pGame.add(bStart);
        pGame.add(bStop);
        pGame.add(bReset);
        pGame.setVisible(false);
        add(pGame);

        // ربط الأحداث
        myAc handler = new myAc();
        b1.addActionListener(handler);
        b2.addActionListener(handler);
        ch.addActionListener(handler);
        itBlack.addActionListener(handler);
        itWhite.addActionListener(handler);
        itBold.addActionListener(handler);
        itItalic.addActionListener(handler);
        itView.addActionListener(handler);
        itInsert.addActionListener(handler);
        itUpdate.addActionListener(handler);
        itPlay.addActionListener(handler);
        bNext.addActionListener(handler);
        bBack.addActionListener(handler);
        bInsert.addActionListener(handler);
        bUpNext.addActionListener(handler);
        bUpBack.addActionListener(handler);
        bUpdate.addActionListener(handler);
        bStart.addActionListener(handler);
        bStop.addActionListener(handler);
        bReset.addActionListener(handler);
    }

    // كلاس اللعبة بالثريد ورسم الدائرة الحمراء والعداد بداخلها
    class GamePanel extends JPanel implements Runnable {
        Thread t;
        Boolean running = false;
        int sec = 0;
        int part = 0;

        public void start() {
            if (t != null && t.isAlive()) {
                return;
            }
            running = true;
            t = new Thread(this);
            t.start();
        }

        public void stop() {
            running = false;
            if (sec == 10 && part == 0) {
                JOptionPane.showMessageDialog(null, "Great! You stopped at 10.00");
            }
        }

        public void reset() {
            running = false;
            sec = 0;
            part = 0;
            repaint();
        }

        @Override
        public void run() {
            while (running) {
                try {
                    Thread.sleep(10);
                    if (!running) {
                        break;
                    }
                    part++;
                    if (part == 100) {
                        sec++;
                        part = 0;
                    }
                    repaint();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillOval(290, 110, 220, 220);

            String timeText;
            if (part < 10) {
                timeText = sec + ".0" + part;
            } else {
                timeText = sec + "." + part;
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Serif", Font.BOLD, 45));
            g.drawString(timeText, 355, 235);
        }
    }

    // الاتصال الأول: جدول المستخدمين لتسجيل الدخول
    public final void connectUser() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "");
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery("select * from mysql.caruser");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    // الاتصال الثاني: جدول السيارات
    public final void connectCar() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "");
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery("select * from mysql.car");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void showCar() {
        try {
            String s = Integer.toString(rs.getInt(3));
            lName.setText("Car Name : " + rs.getString(1));
            lModel.setText("Car Model : " + rs.getString(2));
            lPrice.setText("Car Price : " + s);
            if (getClass().getResource(rs.getString(4)) != null) {
                Icon carPic = new ImageIcon(getClass().getResource(rs.getString(4)));
                lPic.setIcon(carPic);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void showUpdateCar() {
        try {
            String s = Integer.toString(rs.getInt(3));
            tUp1.setText(rs.getString(1));
            tUp2.setText(rs.getString(2));
            tUp3.setText(s);
            tUp4.setText(rs.getString(4));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void changeColor(Color bg, Color fg) {
        getContentPane().setBackground(bg);
        pWelcome.setBackground(bg);
        pView.setBackground(bg);
        pInsert.setBackground(bg);
        pUpdate.setBackground(bg);
        pGame.setBackground(bg);

        lWelcome.setForeground(fg);
        lName.setForeground(fg);
        lModel.setForeground(fg);
        lPrice.setForeground(fg);
        lIn1.setForeground(fg);
        lIn2.setForeground(fg);
        lIn3.setForeground(fg);
        lIn4.setForeground(fg);
        lUp1.setForeground(fg);
        lUp2.setForeground(fg);
        lUp3.setForeground(fg);
        lUp4.setForeground(fg);
        lGameTitle.setForeground(fg);
    }

    public void changeFont(int style) {
        f = new Font("Serif", style, 20);
        fBig = new Font("Serif", style, 30);

        lWelcome.setFont(fBig);
        lGameTitle.setFont(fBig);
        lName.setFont(f);
        lModel.setFont(f);
        lPrice.setFont(f);
        lIn1.setFont(f);
        lIn2.setFont(f);
        lIn3.setFont(f);
        lIn4.setFont(f);
        lUp1.setFont(f);
        lUp2.setFont(f);
        lUp3.setFont(f);
        lUp4.setFont(f);
    }

    private class myAc implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // زر تسجيل الدخول
            if (e.getSource() == b1) {
                connectUser();
                try {
                    rs = st.executeQuery("Select * from mysql.caruser where uid='" + t1.getText() + "' and upw='" + pw.getText() + "' ");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Welcome " + rs.getString(2));
                        pLogin.setVisible(false);
                        bar.setVisible(true);
                        pWelcome.setVisible(true);
                        setSize(800, 650);
                        setLocationRelativeTo(null);
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error password or user id try again");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

            // زر المسح
            if (e.getSource() == b2) {
                t1.setText("");
                pw.setText("");
            }

            // إظهار وإخفاء كلمة المرور
            if (e.getSource() == ch) {
                if (ch.isSelected()) {
                    pw.setEchoChar((char) 0);
                    ch.setText("Hide password");
                } else {
                    pw.setEchoChar('*');
                    ch.setText("Show password");
                }
            }

            // إعدادات اللون
            if (e.getSource() == itBlack) {
                changeColor(Color.BLACK, Color.WHITE);
            }
            if (e.getSource() == itWhite) {
                changeColor(Color.WHITE, Color.BLACK);
            }

            // إعدادات شكل الخط
            if (e.getSource() == itBold) {
                changeFont(Font.BOLD);
            }
            if (e.getSource() == itItalic) {
                changeFont(Font.ITALIC);
            }

            // خيار View
            if (e.getSource() == itView) {
                pWelcome.setVisible(false);
                pInsert.setVisible(false);
                pUpdate.setVisible(false);
                pGame.setVisible(false);
                pView.setVisible(true);
                connectCar();
                try {
                    if (rs.next()) {
                        showCar();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

            // خيار Insert
            if (e.getSource() == itInsert) {
                pWelcome.setVisible(false);
                pView.setVisible(false);
                pUpdate.setVisible(false);
                pGame.setVisible(false);
                pInsert.setVisible(true);
            }

            // خيار Update
            if (e.getSource() == itUpdate) {
                pWelcome.setVisible(false);
                pView.setVisible(false);
                pInsert.setVisible(false);
                pGame.setVisible(false);
                pUpdate.setVisible(true);
                connectCar();
                try {
                    if (rs.next()) {
                        showUpdateCar();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

            // خيار Play في قائمة Game
            if (e.getSource() == itPlay) {
                pWelcome.setVisible(false);
                pView.setVisible(false);
                pInsert.setVisible(false);
                pUpdate.setVisible(false);
                pGame.setVisible(true);
            }

            // أزرار اللعبة
            if (e.getSource() == bStart) {
                pGame.start();
            }
            if (e.getSource() == bStop) {
                pGame.stop();
            }
            if (e.getSource() == bReset) {
                pGame.reset();
            }

            // أزرار التنقل في شاشة View
            if (e.getSource() == bNext) {
                try {
                    if (rs.next()) {
                        showCar();
                    } else {
                        JOptionPane.showMessageDialog(null, "End of file");
                        rs.previous();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

            if (e.getSource() == bBack) {
                try {
                    if (rs.previous()) {
                        showCar();
                    } else {
                        JOptionPane.showMessageDialog(null, "begin of the file");
                        rs.next();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

            // زر الإضافة
            if (e.getSource() == bInsert) {
                connectCar();
                try {
                    rs.moveToInsertRow();
                    rs.updateString(1, tIn1.getText());
                    rs.updateString(2, tIn2.getText());
                    rs.updateInt(3, Integer.parseInt(tIn3.getText()));
                    rs.updateString(4, tIn4.getText());
                    rs.insertRow();
                    JOptionPane.showMessageDialog(null, "row inserted correctly");
                    tIn1.setText("");
                    tIn2.setText("");
                    tIn3.setText("");
                    tIn4.setText("");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

            // أزرار التنقل والتعديل في شاشة Update
            if (e.getSource() == bUpNext) {
                try {
                    if (rs.next()) {
                        showUpdateCar();
                    } else {
                        JOptionPane.showMessageDialog(null, "End of file");
                        rs.previous();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

            if (e.getSource() == bUpBack) {
                try {
                    if (rs.previous()) {
                        showUpdateCar();
                    } else {
                        JOptionPane.showMessageDialog(null, "begin of the file");
                        rs.next();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

            if (e.getSource() == bUpdate) {
                try {
                    rs.moveToCurrentRow();
                    rs.updateString(1, tUp1.getText());
                    rs.updateString(2, tUp2.getText());
                    rs.updateInt(3, Integer.parseInt(tUp3.getText()));
                    rs.updateString(4, tUp4.getText());
                    rs.updateRow();
                    JOptionPane.showMessageDialog(null, "row updated correctly");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }

    public static void main(String[] args) {
        Car myf = new Car();
        myf.setVisible(true);
        myf.setSize(600, 500);
        myf.setLocationRelativeTo(null);
        myf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
