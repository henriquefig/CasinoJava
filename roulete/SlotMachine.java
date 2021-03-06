// 2º Semestre 2016 Trabalho 1
// Henrique Figueiredo
package roulete;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// classe do jogo da SlotMachine
public class SlotMachine extends JFrame {
   User player=new User();
   Header cabecalho=new Header(player);
   PlayerSong musica=new PlayerSong();
   JPanel linhas=new JPanel(new GridLayout(0,3));
   JPanel board=new JPanel();
   JPanel preco=new JPanel();
   JLabel inliber,liber,boardi;
   JPanel alavanc=new JPanel();
   SlotLinha um=new SlotLinha();
   SlotLinha dois=new SlotLinha();
   SlotLinha tres=new SlotLinha();
    private Timer spining,spining2;
    CardLayout c1 = (CardLayout) (um.getLayout());
    CardLayout c2 = (CardLayout) (dois.getLayout());
    CardLayout c3 = (CardLayout) (tres.getLayout());
    private int rand,rand1,rand2,value=0,value1=0,value2=0,selector=0,flagstop=1,wining=0;

    public SlotMachine() {
    super("Lucky BAR");
    setSize(930, 475);
    setLayout(new BorderLayout());
    board.setLayout(new OverlayLayout(board));

    c1.first(um);
    c2.first(dois);
    c3.first(tres);
    um.linha.get(0).imgcard.setVisible(false);
    dois.linha.get(0).imgcard.setVisible(false);
    tres.linha.get(0).imgcard.setVisible(false);

     // criar alavanca para começar jogo
    Icon inleaver=new ImageIcon("./img/slot/alav3.png");
    inliber = new JLabel(inleaver);
    Icon precario=new ImageIcon("./img/slot/precario.png");
    JLabel libel = new JLabel(precario);
    Icon base=new ImageIcon("./img/slot/base.png");
    boardi = new JLabel(base);
    board.add(boardi);
    libel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
    preco.setVisible(true);
    preco.add(libel); 
    Icon leaver=new ImageIcon("./img/slot/alav.png");
    liber = new JLabel(leaver);
    liber.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
    alavanc.setVisible(true);
    alavanc.add(liber); 
    alavanc.addMouseListener(new MouseAdapter() { 
    public void mouseClicked(MouseEvent e){
               // caso a alavanca seja permida e o utilizador tenha saldo começa o Timer de acordo com 3 números random
              if(player.getSaldo() > 0 && player.getBet() > 0 && flagstop==1)
              {
                alavanc.remove(liber); 
                 alavanc.add(inliber); 
                player.setSaldo(player.getSaldo()-player.getBet());
                cabecalho.saldo.setText("Saldo: "+player.getSaldo());
                selector=1;
                rand=rand+new Random().nextInt(80);
                rand=rand+50;
                rand1=rand+new Random().nextInt(60);
                rand2=rand1+new Random().nextInt(50);
                flagstop=0;
                spining2=new Timer(30,new Flipping());
                spining2.start();  
              }
          }

        }
        );
    cabecalho.exit.addMouseListener(new MouseAdapter() { 
    public void mouseClicked(MouseEvent e){
        new Casino().setVisible(true);
          setVisible(false);
          }

        });
    cabecalho.bet.setVisible(false);
    add(cabecalho,BorderLayout.NORTH);
    add(preco,BorderLayout.WEST);
    linhas.add(um);
    linhas.add(dois);
    linhas.add(tres);
    JPanel centro=new JPanel();
    centro.add(board);
    centro.add(linhas);
    add(centro,BorderLayout.CENTER);
    add(alavanc,BorderLayout.EAST);
    add(musica,BorderLayout.SOUTH);


    }
   // classe que implementa o Timer que move os paineis atraves da classe movingPanel
    public class Flipping implements ActionListener { 
      public void actionPerformed(ActionEvent e) 
      {
        if(selector==1)
        {
          new movingPanel(um,selector);
           selector=2;
        }
        if(selector==2)
        {
            new movingPanel(dois,selector);
           selector=3;
        }
        if(selector==3)
        {
            new movingPanel(tres,selector);
           selector=1;
        }
      }
    }
   // classe que implementa a mudança de cada painel através do CardLayout
  public class movingPanel
  {
     movingPanel(SlotLinha a,int i)
      {
        if(i == 1 && value<rand)
        {
          c1.next(a);
          value++;
        }
        if(i == 2 && value1<rand1)
        {
          c2.next(a);
          value1++;

        }
        if(i == 3 && value2<rand2)
        {
          c3.next(a);
          value2++;
        }
        if(value==rand && value1==rand1 && value2==rand2)
        {
          alavanc.remove(inliber);
          alavanc.add(liber);
          wining=getWinning();
       
          spining2.stop();
          player.setSaldo(player.getSaldo()+player.getBet()*(wining+1));
          cabecalho.saldo.setText("Saldo: "+player.getSaldo());
          flagstop=1;
        }
      }
  }
   // método que retorna os ganhos no final de uma jogada
  public int getWinning() {
      int a=-1,b=0,cont=0,c=0,d=0;
      cont=value/316;
        b=value-316*cont;
      cont= value1/316;
        c=value1-316*cont;
      cont= value2/316;
        d=value2-316*cont;
      if(um.linha.get(b).getRank() == dois.linha.get(c).getRank() && dois.linha.get(c).getRank()==tres.linha.get(d).getRank())
      {
        switch(um.linha.get(b).getRank())
        {
          case 0:
          a=50;break;
          case 1:
          a=100;break;
          case 2:
          a=500;break;
          case 3:
          a=1;break;
          case 4:
          a=5;break;
          case 5:
          a=2;break;
          case 6:
          a=10;break;
          case 7:
          a=200;break;
          case 8:
          a=1000;break;
        }
      }
      return a;
    }
   public static void main(String[] args) {
    new Casino().setVisible(true);
  }
}
