package Test;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;



@SuppressWarnings("serial")
public class RoomAWT extends Frame implements ActionListener , WindowListener{

   MenuItem mi_reserve, mi_cancel, mi_check, mi_exit;
   HashMap<Integer, String> hm_room;
   Button bt_res, bt_cancel;
   Checkbox[] cb;
   TextField tf_res, tf_cancel, tf_cancel_bot;

   public RoomAWT() {
      
      hm_room = new HashMap<Integer, String>();
      cb = new Checkbox[9];
      int num = 100;
      for (int i = 0; i < 9; i++) {
         if (i != 0 && i % 3 == 0) {
            num += 98;
         } else {
            num++;
         }

         hm_room.put(num, "empty room");
         String num_s = "" + (num);
         cb[i] = new Checkbox(num_s);
      }

      this.setLayout(new BorderLayout(30, 30));
      Label lb_title = new Label("Book  Ver2.0", Label.CENTER);
      this.add(lb_title, BorderLayout.CENTER);
      MenuBar mb = new MenuBar();
      this.setMenuBar(mb);
      Menu m = new Menu("menu");
      mb.add(m);
      mi_reserve = new MenuItem("Reserve");
      mi_cancel = new MenuItem("Cancle");
      mi_check = new MenuItem("Status");
      mi_exit = new MenuItem("Exist");
      m.add(mi_reserve);
      m.add(mi_cancel);
      m.add(mi_check);
      m.addSeparator(); // addSeparator(); 구분자.
      m.add(mi_exit);

      mi_reserve.addActionListener(this);
      mi_cancel.addActionListener(this);
      mi_check.addActionListener(this);
      mi_exit.addActionListener(this);
      this.addWindowListener(this);

   }

   public void reserve() {
      this.removeAll();
      this.setLayout(new BorderLayout(30, 30));
      Panel p_res = new Panel(new BorderLayout(30, 30));
      this.add(p_res, BorderLayout.CENTER);
      Label lb_res_title = new Label("book reservation", Label.CENTER);
      this.add(lb_res_title, BorderLayout.NORTH);
      Panel p_res_in = new Panel(new GridLayout(3, 3, 10, 10));
      this.add(p_res_in, BorderLayout.CENTER);

      Panel p_res_bot = new Panel(new BorderLayout(30, 30));
      this.add(p_res_bot, BorderLayout.SOUTH);

      tf_res = new TextField();
      // tf_res.setEnabled(false); //이경우 완전한 비활성화. 출력조차 못함.
      tf_res.setEditable(false);
      bt_res = new Button("book");
      p_res_bot.add(tf_res, BorderLayout.CENTER);
      p_res_bot.add(bt_res, BorderLayout.EAST);

      for (int i = 0; i < 9; i++) {
         p_res_in.add(cb[i]); // 체크박스 삽입
         
         int room_num = Integer.parseInt(cb[i].getLabel()); //room_num에 체크박스 라벨추출
         if (hm_room.get(room_num).equals("booked!")) {//HashMap의 value를 뽑아서 문장 비교
            cb[i].setEnabled(false); //체크박스 비활성화.
         }

      }

      bt_res.addActionListener(this);

   }

   public void cancel() {
      this.removeAll();
      this.setLayout(new BorderLayout(30, 30));
      Label lb_title = new Label("cancle book", Label.CENTER);
      this.add(lb_title, BorderLayout.NORTH);
      Panel pn_cancel = new Panel(new FlowLayout());
      this.add(pn_cancel, BorderLayout.CENTER);
      Label lb_room = new Label("Room number");
      pn_cancel.add(lb_room);
      tf_cancel = new TextField(10);
      pn_cancel.add(tf_cancel);
      bt_cancel = new Button("book cancle");
      pn_cancel.add(bt_cancel);
      bt_cancel.addActionListener(this);

      Panel pn_cancel_bot = new Panel(new BorderLayout(30, 30));
      this.add(pn_cancel_bot, BorderLayout.SOUTH);
      Label lb_cancel_msg = new Label("message");
      pn_cancel_bot.add(lb_cancel_msg, BorderLayout.WEST);
      tf_cancel_bot = new TextField();
      pn_cancel_bot.add(tf_cancel_bot, BorderLayout.CENTER);
      tf_cancel_bot.setEditable(false);

   }

   public void check() {
      this.removeAll();
      this.setLayout(new BorderLayout(30, 30));
      Label lb_check = new Label("reservation status", Label.CENTER);
      this.add(lb_check, BorderLayout.NORTH);
      TextArea ta_check = new TextArea();
      ta_check.setRows(hm_room.size());
      ta_check.setEditable(false);
      this.add(ta_check, BorderLayout.CENTER);

      for (int i : hm_room.keySet()) {
         ta_check.append(i + "Room:" + hm_room.get(i) + "\r\n");
      }

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      Object obj = e.getSource();

      if (obj == bt_res) {

         for (int i = 0; i < cb.length; i++) {
            if (cb[i].getState() == true) {
               cb[i].setEnabled(false); // 비활성화
               
               hm_room.put((int) Integer.parseInt(cb[i].getLabel()), "booked!");
               tf_res.setText("reservation complete");
               cb[i].setState(false); //체크해제
            }

         }

      }

      if (obj == bt_cancel) {
         try {
            int cancelroom_info = Integer.parseInt(tf_cancel.getText());

            if (hm_room.get(cancelroom_info).equals("empty room")) {
               hm_room.put(cancelroom_info, "empty room");
               tf_cancel_bot.setText(cancelroom_info + "room book cancled");
               for (int i = 0; i < hm_room.size(); i++) {//for
                  if(cb[i].getLabel().equals(tf_cancel.getText())) {
                     cb[i].setEnabled(true);
                  }
               }
            }else if (hm_room.get(cancelroom_info).equals("empty room")) {
               tf_cancel_bot.setText("The room is empty.");
            }
         } catch (Exception ex) {
            tf_cancel_bot.setText("There is no room impormation");
         }
      }

      if (obj == mi_reserve){
         this.reserve();
         this.validate();
      } else if (obj == mi_cancel) {
         this.cancel();
         this.validate();
      } else if (obj == mi_check) {
         this.check();
         this.validate();
      } else if (obj == mi_exit) {
         System.exit(0);
      }

   }
   
   @Override
   public Insets getInsets() {
      Insets i = new Insets(50, 30, 30, 30);
      return i;
   }
   


   @Override
   public void windowOpened(WindowEvent e) {}

   @Override
   public void windowClosing(WindowEvent e) {
      System.exit(0);
      
   }

   @Override
   public void windowClosed(WindowEvent e) {}

   @Override
   public void windowIconified(WindowEvent e) {}

   @Override
   public void windowDeiconified(WindowEvent e) {}

   @Override
   public void windowActivated(WindowEvent e) {}

   @Override
   public void windowDeactivated(WindowEvent e) {}

   public static void main(String[] args) {          //main
      
      RoomAWT r = new RoomAWT();
      
      r.setSize(300, 300);
      r.setVisible(true);
      
   }
}