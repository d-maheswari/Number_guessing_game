import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Game {
    private Label OkLabel;
    private TextField OkField;
    private Button OkButton;
    private Button hintButton;
    private Button playagain;
    private TextArea result;
    private Frame f;
    private int randNum;
    private int chances;

    public Game() {
        f=new Frame();
        
        f.setTitle("PLAYING WITH NUMBERS");
        f.setSize(400, 400);
        f.setLayout(new FlowLayout());
        f.setBackground(new Color(103,153,180));
        
        Font labelfont=new Font("Arial", Font.BOLD + Font.ITALIC, 20);
        Font textAreafont=new Font("Times New Roman",Font.ITALIC, 16);
        OkLabel = new Label("Guess the number:");
        OkField = new TextField(10);
        OkButton = new Button("Ok");
        OkButton.setForeground(Color.white);
        OkButton.setBackground(new Color(255-102-102));
        hintButton = new Button("Hint");
        hintButton.setForeground(Color.white);
        hintButton.setBackground(new Color(150,50,200));
        playagain = new Button("Play Again");
        
        playagain.setBackground(Color.BLUE);
        
        result = new TextArea(20,50);
        result.setEditable(false);
        OkLabel.setFont(labelfont);
        OkLabel.setFont(labelfont);
        result.setFont(textAreafont);
        f.add(result);

        GridBagLayout grid = new GridBagLayout(); 
        GridBagConstraints gbc = new GridBagConstraints();  
        f.setLayout(grid);   
        GridBagLayout layout = new GridBagLayout();  
        f.setLayout(layout);  
        gbc.fill = GridBagConstraints.HORIZONTAL;  
        gbc.gridx = 0;  
        gbc.gridy = 0;  
        f.add(OkLabel, gbc);  
        gbc.fill = GridBagConstraints.HORIZONTAL;  
        gbc.ipady = 20;  
        gbc.gridx = 0;  
        gbc.gridy = 1;  
        f.add(OkField, gbc);  
        gbc.gridx = 0;  
        gbc.gridy = 2;  
        f.add(OkButton, gbc);  
        gbc.gridx = 0;  
        gbc.gridy = 3;  
        gbc.fill = GridBagConstraints.HORIZONTAL;  
        gbc.gridwidth = 2;  
        f.add(hintButton, gbc);  
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=3;
        f.add(playagain,gbc);
    
        OkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveHint();
            }
        });
        playagain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        f.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent w)
            {
                System.exit(0);
            }
        });
        resetGame();
        f.setVisible(true);
    }

    private void resetGame() {
        Random random = new Random();
        randNum = random.nextInt(50) + 1; 
        chances = 0;
        OkField.setEnabled(true);
        OkButton.setEnabled(true);
        hintButton.setEnabled(true);
        result.setBackground(Color.WHITE);
        result.setText("----Number between 1 and 50----\n");
        result.setForeground(Color.BLACK);
        
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(OkField.getText());
            chances++;
           if(chances<=5){
            if (guess == randNum) {
                result.append("YOU DID IT,it's a correct answer.\n");
                OkField.setEnabled(false);
                OkButton.setEnabled(false);
                hintButton.setEnabled(false);
                result.setForeground(Color.GREEN);
                result.setBackground(Color.BLACK);
                //resetGame();
            } else if (guess < randNum) {
                result.append("Wrong Guess!\nEnter Big number.\n");
                result.setForeground(Color.BLUE);
            } else {
                result.append("Wrong Guess\nEnter small number.\n");
                result.setForeground(Color.BLACK);
            }
        }else{
            result.append("You have used all your chances!!!\n");
            result.append("CORRECT ANSWER "+randNum+"\n");
            result.append("If You wanna play again click on 'Play Again'\n");
            //resetGame();
        }
        } catch (NumberFormatException e) {
            result.append("You can enter only positive Integers!!!\n");
        }

        OkField.setText("");
        OkField.requestFocus();
    }

    private void giveHint() {
        int lb = Math.max(1, randNum - 3);
        int ub = Math.min(100, randNum + 3);
        result.append("Hint: Choose between " + lb + " and " + ub + ".\n");
    }

    public static void main(String[] args) {
        new Game();
    }
}