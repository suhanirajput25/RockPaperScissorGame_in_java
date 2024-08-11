import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

public class RockPaperScissor {
    public static void main(String[] args) {
        Game.panel_introduction();
        Game.panel_game();
    }
}

class Game {
    static int score_human;
    static int score_win = 0;
    static int score_total = 0;
    static int score_tie = 0;

    public static void panel_introduction() { 
        String info_text = "Rock, Paper, Scissors! This game is fairly simple.\nSimply pick your hands whenever you are ready.\nRock beats scissors, scissors beat paper\nand paper wraps the rock.";
        JOptionPane.showMessageDialog(null, info_text, "How to play!", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void panel_game() {
        JFrame frame_main = new JFrame("Rock, Scissors, Paper");
        frame_main.getContentPane().setBackground(Color.BLACK);
        Container panel_main = frame_main.getContentPane();
        panel_main.setLayout(null);

        JLabel title_main = new JLabel("Rock Paper Scissors");
        title_main.setBounds(240, 20, 400, 40);
        title_main.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 22));
        title_main.setForeground(Color.WHITE);

        JButton btn_rock = new JButton(new ImageIcon("0.png"));
        btn_rock.setBackground(Color.RED);
        btn_rock.setBounds(40, 100, 200, 250);

        JButton btn_paper = new JButton(new ImageIcon("1.png"));
        btn_paper.setBackground(Color.YELLOW);
        btn_paper.setBounds(290, 100, 200, 250);

        JButton btn_scissors = new JButton(new ImageIcon("2.png"));
        btn_scissors.setBackground(Color.BLUE);
        btn_scissors.setBounds(540, 100, 200, 250);

        JToggleButton toggle_button = new JToggleButton("Light Mode");
        toggle_button.setBounds(570, 20, 150, 40);
        toggle_button.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    frame_main.getContentPane().setBackground(Color.WHITE);
                    title_main.setForeground(Color.BLACK);
                    toggle_button.setText("Dark Mode");
                } else {
                    frame_main.getContentPane().setBackground(Color.BLACK);
                    title_main.setForeground(Color.WHITE);
                    toggle_button.setText("Light Mode");
                }
            }
        });

        panel_main.add(toggle_button);
        panel_main.add(btn_rock);
        panel_main.add(btn_paper);
        panel_main.add(btn_scissors);
        panel_main.add(title_main);

        btn_rock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                compute_winner(0); // 0 for rock
            }
        });

        btn_paper.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                compute_winner(1); // 1 for paper
            }
        });

        btn_scissors.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                compute_winner(2); // 2 for scissors
            }
        });

        frame_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_main.setSize(800, 400);
        frame_main.setVisible(true);
    }

    public static void compute_winner(int choice_human) {
        int choice_computer = (int) (Math.random() * 3);
        String label_choice = "";
        String label_winner = "";

        if (choice_human == choice_computer) {
            label_choice = "It is a tie!";
            score_tie += 1;
        } else if ((choice_human == 0 && choice_computer == 2) || // Rock beats Scissors
                   (choice_human == 1 && choice_computer == 0) || // Paper beats Rock
                   (choice_human == 2 && choice_computer == 1)) { // Scissors beat Paper
            label_choice = "You win!";
            score_win += 1;
        } else {
            label_choice = "Computer wins!";
        }

        score_total += 1;

        JFrame score_frame = new JFrame("Game Result");
        score_frame.getContentPane().setBackground(Color.CYAN);
        Container score_panel = score_frame.getContentPane();
        score_panel.setLayout(null);

        JLabel label_result = new JLabel(label_choice);
        label_result.setBounds(150, 10, 300, 35);
        score_panel.add(label_result);

        JLabel label_title_human = new JLabel("Human's Choice");
        label_title_human.setBounds(50, 35, 150, 35);
        score_panel.add(label_title_human);

        JLabel label_title_computer = new JLabel("Computer's Choice");
        label_title_computer.setBounds(350, 35, 150, 35);
        score_panel.add(label_title_computer);

        JLabel image_human = new JLabel(new ImageIcon( choice_human + ".png"));
        image_human.setBounds(10, 100, 200, 250);
        score_panel.add(image_human);

        JLabel image_computer = new JLabel(new ImageIcon( choice_computer + "c.png"));
        image_computer.setBounds(300, 100, 200, 250);
        score_panel.add(image_computer);

        JLabel label_score1 = new JLabel("Win / Total : " + score_win + "/" + score_total);
        label_score1.setBounds(175, 200, 150, 350);
        score_panel.add(label_score1);

        JLabel label_score2 = new JLabel("Tie: " + score_tie);
        label_score2.setBounds(175, 210, 125, 370);
        score_panel.add(label_score2);

        JButton btn_ok = new JButton("OK");
        btn_ok.setBackground(Color.GREEN);
        btn_ok.setBounds(410, 360, 100, 50);
        score_panel.add(btn_ok);

        btn_ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                score_frame.dispose();
            }
        });

        score_frame.setSize(600, 450);
        score_frame.setVisible(true);
    }
}