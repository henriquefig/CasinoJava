// 2 º Semestre Trabalho 1
// Henrique Figueiredo

package roulete;
import java.util.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Blackjack extends JFrame{

  private static int saldo=1000,score=0,botscore=0,bet=0,counter=0,counter1=0,score2=0,botcounter=0,acecounter=0,botacecounter=0,segundamao=0;
   User player=new User();
   User computer=new User();
   Deck baralho=new Deck();

   JPanel cenas=new JPanel(new GridLayout(7,0));
  
  public Blackjack() {
    super("Blackjack 21 - Lucks");
    setSize(670, 770);
    JTextArea computerhand = new JTextArea("Cartas computador: Score :-");
    JTextArea mao = new JTextArea();
    JPanel bot= new JPanel();
    String back="./img/Deck/back.jpeg";
    Icon image=new ImageIcon(back);
    JLabel label[] = new JLabel[4];
    JPanel tuamao=new JPanel();
    PlayerSong musica=new PlayerSong();
    JTextArea scoremao = new JTextArea("Score=-");
    JButton hit =new JButton("Hit Me!");
    JButton split =new JButton("Split");
    JButton stand =new JButton("Stand");
    JButton segunda =new JButton("Click-me!");
    JTextArea resultado = new JTextArea();
    JTextArea ganhou = new JTextArea("Blackjack! Ganhou!");
    JPanel action=new JPanel();
    JPanel header= new JPanel();
    JPanel headerbot= new JPanel();
    JPanel Cartscor= new JPanel();
    Header cabeçalho = new Header(player);
  
    hit.setVisible(false);
    stand.setVisible(false);
    split.setVisible(false);
    resultado.setVisible(false);
    ganhou.setVisible(false);
    segunda.setVisible(false);
    segunda.addMouseListener(new MouseAdapter(){
    	public void mouseClicked(MouseEvent e)
    	{
          resultado.setVisible(false);
          hit.setVisible(true);
          stand.setVisible(true);
          split.setVisible(true);
     	 segunda.setVisible(false);
          for(int air=0;air<counter+3;air++)
          {
          	player.getBlackcards(air).imgcard.setVisible(false);

          }
          player.getBlackcards(1).imgcard.setVisible(true);
          player.getBlackcards(3).imgcard.setVisible(true);
          score=0;
          score2=getCardsScore(player.getBlackcards(1),0);
          score2=getCardsScore(player.getBlackcards(3),score2);
          scoremao.setText("Score2="+score2);
          segundamao++;
          acecounter=0;
    	}
    });
   
    player.setBlackcards(baralho.drawFromDeck(),0);
    player.setBlackcards(baralho.drawFromDeck(),1);
    computer.setBlackcards(baralho.drawFromDeck(),0);
    computer.setBlackcards(baralho.drawFromDeck(),1);

    

    for(int i=0;i<4;i++)
    label[i]=new JLabel(image);
    cabeçalho.exit.addMouseListener(new MouseAdapter() { 
    public void mouseClicked(MouseEvent e){
      musica.backtheme.Stopsound();
      setVisible(false); //you can't see me!
      dispose();
        new Casino().setVisible(true);          
      }

        });
    cabeçalho.bet.addMouseListener(new MouseAdapter() { 
    public void mouseClicked(MouseEvent e){
      if(player.getSaldo()>=player.getBet() && player.getBet()!=0)
      {
        baralho=new Deck();
        acecounter=0;
        botacecounter=0;
		counter1=0;
        counter=0;
      	  score=0;
          score2=0;
        hit.setVisible(true);
        stand.setVisible(true);
        split.setVisible(true);
        segunda.setVisible(false);
        label[0].setVisible(true);
        resultado.setVisible(false);
        tuamao.removeAll();
       bot.removeAll();
        repaint();
        revalidate();
        botcounter=0;
        player.setBlackcards(baralho.drawFromDeck(),0);
        player.setBlackcards(baralho.drawFromDeck(),1);
        computer.setBlackcards(baralho.drawFromDeck(),0);
        computer.setBlackcards(baralho.drawFromDeck(),1);
        for(int i=0;i<=1;i++)
        {
          if(player.hasAce(player.getBlackcards(i))==true)
          {
              acecounter++;
          }
        }
        for(int i=0;i<=1;i++)
        {
          if(computer.hasAce(computer.getBlackcards(i))==true)
          {
              botacecounter++;
          }
        }
        computerhand.setText("Cartas computador: Score :"+getCardsScore(computer.getBlackcards(0),0));
        score=getCardsScore(player.getBlackcards(0),0);
        score=getCardsScore(player.getBlackcards(1),score);
        botscore=getCardsScore(computer.getBlackcards(0),0);
        botscore=getCardsScore(computer.getBlackcards(1),botscore);
         if(botacecounter==2)
          {
            botscore=botscore-10;
            botacecounter--;
          }
         if(acecounter==2)
         {
            score=score-10;
            acecounter--;
         }
          player.setSaldo(player.getSaldo()-player.getBet());
          cabeçalho.saldo.setText("Saldo:"+player.getSaldo()+"$");
          for(int i=1;i<4;i++)
            label[i].setVisible(false);

          tuamao.add(player.getBlackcards(0).imgcard);
          tuamao.add(player.getBlackcards(1).imgcard);
          bot.add(computer.getBlackcards(0).imgcard);
            scoremao.setText("Score="+score);
          

          }
        }

        });
    split.addMouseListener(new MouseAdapter() { 
          public void mouseClicked(MouseEvent e){
              if (player.getBlackcards(0).getRank()==player.getBlackcards(1).getRank() && player.getSaldo()>=player.getBet() && counter==0) {
                 player.setBlackcards(baralho.drawFromDeck(),2);  
                  player.setBlackcards(baralho.drawFromDeck(),3); 
                  score=getCardsScore(player.getBlackcards(0),0);
                  score=getCardsScore(player.getBlackcards(2),score);
                  score2=getCardsScore(player.getBlackcards(1),0);
                  score2=getCardsScore(player.getBlackcards(3),score2);
                  scoremao.setText("Split! Score1="+score);
                  player.getBlackcards(1).imgcard.setVisible(false);
                  tuamao.add(player.getBlackcards(2).imgcard);
                  }
                }

              });
          stand.addMouseListener(new MouseAdapter() { 
          public void mouseClicked(MouseEvent e){
              hit.setVisible(false);
              stand.setVisible(false);
              split.setVisible(false);
              label[0].setVisible(false);
              bot.add(computer.getBlackcards(1).imgcard);
               while(botscore<17)
                {
                  botcounter++;
                  computer.setBlackcards(baralho.drawFromDeck(),botcounter+1);
                  botscore=getCardsScore(computer.getBlackcards(botcounter+1),botscore);
                  computer.getBlackcards(botcounter+1).imgcard.setVisible(true);
                  bot.add(computer.getBlackcards(botcounter+1).imgcard);
                
                  if(computer.hasAce(computer.getBlackcards(botcounter+1))==true)
                    {
                        botacecounter++;
                     } 
                   if(botscore>21)
                   {
                       if(botacecounter>0)
                        {

                         while(botacecounter>0)
                          {
                            botscore=botscore-10;
                            botacecounter--;
                          }
                        }
                    }
                }

          		if (score2==0) 
          		{
                    if(botscore<21)
                    {
                      if(botscore<score)
                      {
                        resultado.setText("Vencedor! Recebe -"+player.getBet()+"$");
                        resultado.setVisible(true);
                        player.setSaldo(player.getSaldo()+2*player.getBet());
                      }
                      if(botscore==score)
                      {
                        if(botcounter==counter)
                        {
                          resultado.setText("Empate! A sua aposta foi retornada!");
                          resultado.setVisible(true);
                          player.setSaldo(player.getSaldo()+player.getBet());
                        }
                        if(botcounter>counter)
                        {
                          resultado.setText("Vencedor! Recebe -"+player.getBet()+"$");
                          resultado.setVisible(true);
                          player.setSaldo(player.getSaldo()+2*player.getBet());
                        }
                        if(botcounter<counter)
                        {
                          resultado.setText("Perdedor! Tenta outra vez (Dica, aposta mais)!");
                          resultado.setVisible(true);
                          if(player.getBet()>=player.getSaldo())
                          {
                            player.setBet(player.getSaldo());
                            cabeçalho.bettingarea.setText("bet value: "+player.getBet()+"$");
                          }
                        }
                      }
                      if(botscore>score)
                      {
                        resultado.setText("Perdedor! Tenta outra vez (Dica, aposta mais)!");
                        resultado.setVisible(true);
                         if(player.getBet()>=player.getSaldo())
                          {
                            player.setBet(player.getSaldo());
                            cabeçalho.bettingarea.setText("bet value: "+player.getBet()+"$");
                          }
                      }
                    }
                    if(botscore==21)
                    {
                      if(botscore==score)
                      {
                        if(botcounter<counter)
                        {
                          resultado.setText("Perdedor! Tenta outra vez (Dica, aposta mais)!");
                          resultado.setVisible(true);
                          if(player.getBet()>=player.getSaldo())
                          {
                            player.setBet(player.getSaldo());
                            cabeçalho.bettingarea.setText("bet value: "+player.getBet()+"$");
                          }
                        }
                        if(botcounter==counter)
                        {
                          resultado.setText("Empate! A sua aposta foi retornada!");
                          resultado.setVisible(true);
                          player.setSaldo(player.getSaldo()+player.getBet());
                        }
                        if(botcounter>counter)
                        {
                          resultado.setText("Vencedor! Recebe -"+player.getBet()+"$");
                          resultado.setVisible(true);
                          player.setSaldo(player.getSaldo()+2*player.getBet());
                        }
                      }
                    }
                    if(botscore>21)
                    {
                      resultado.setText("Bot Bust! Vencedor! Recebe -"+player.getBet()+"$");
                      resultado.setVisible(true);
                      player.setSaldo(player.getSaldo()+2*player.getBet());
                    }
                    cabeçalho.saldo.setText("Saldo:"+player.getSaldo()+"$");
                    computerhand.setText("Cartas computador: Score :"+botscore);
             }
            else
            {
            	if(segundamao==0)
            	{
            		if(botscore<21)
                    {
                      if(botscore<score)
                      {
                        resultado.setText("Vencedor! Recebe -"+player.getBet()+"$");
                        resultado.setVisible(true);
                        player.setSaldo(player.getSaldo()+2*player.getBet());
                      }
                      if(botscore==score)
                      {
                        if(botcounter==counter)
                        {
                          resultado.setText("Empate! A sua aposta foi retornada!");
                          resultado.setVisible(true);
                          player.setSaldo(player.getSaldo()+player.getBet());
                        }
                        if(botcounter>counter)
                        {
                          resultado.setText("Vencedor! Recebe -"+player.getBet()+"$");
                          resultado.setVisible(true);
                          player.setSaldo(player.getSaldo()+2*player.getBet());
                        }
                        if(botcounter<counter)
                        {
                          resultado.setText("Perdedor! Tenta outra vez (Dica, aposta mais)!");
                          resultado.setVisible(true);
                          if(player.getBet()>=player.getSaldo())
                          {
                            player.setBet(player.getSaldo());
                            cabeçalho.bettingarea.setText("bet value: "+player.getBet()+"$");
                          }
                        }
                      }
                      if(botscore>score)
                      {
                        resultado.setText("Perdedor! Tenta outra vez (Dica, aposta mais)!");
                        resultado.setVisible(true);
                         if(player.getBet()>=player.getSaldo())
                          {
                            player.setBet(player.getSaldo());
                            cabeçalho.bettingarea.setText("bet value: "+player.getBet()+"$");
                          }
                      }
                    }
                    if(botscore==21)
                    {
                      if(botscore==score)
                      {
                        if(botcounter<counter)
                        {
                          resultado.setText("Perdedor! Tenta outra vez (Dica, aposta mais)!");
                          resultado.setVisible(true);
                          if(player.getBet()>=player.getSaldo())
                          {
                            player.setBet(player.getSaldo());
                            cabeçalho.bettingarea.setText("bet value: "+player.getBet()+"$");
                          }
                        }
                        if(botcounter==counter)
                        {
                          resultado.setText("Empate! A sua aposta foi retornada!");
                          resultado.setVisible(true);
                          player.setSaldo(player.getSaldo()+player.getBet());
                        }
                        if(botcounter>counter)
                        {
                          resultado.setText("Vencedor! Recebe -"+player.getBet()+"$");
                          resultado.setVisible(true);
                          player.setSaldo(player.getSaldo()+2*player.getBet());
                        }
                      }
                      else
                      {
                      	  resultado.setText("Perdedor! Clique para jogar segunda mão!");
                          resultado.setVisible(true);
                      }
                    }
                    if(botscore>21)
                    {
                      resultado.setText("Bot Bust! Vencedor! Recebe -"+player.getBet()+"$");
                      resultado.setVisible(true);
                      player.setSaldo(player.getSaldo()+2*player.getBet());
                    }
                    cabeçalho.saldo.setText("Saldo:"+player.getSaldo()+"$");
                    computerhand.setText("Cartas computador: Score :"+botscore);
                 	segunda.setVisible(true);

            	}
            	else
            	{
					if(botscore<21)
                    {
                      if(botscore<score2)
                      {
                        resultado.setText("Vencedor! Recebe -"+player.getBet()+"$");
                        resultado.setVisible(true);
                        player.setSaldo(player.getSaldo()+2*player.getBet());
                      }
                      if(botscore==score2)
                      {
                        if(botcounter==counter1)
                        {
                          resultado.setText("Empate! A sua aposta foi retornada!");
                          resultado.setVisible(true);
                          player.setSaldo(player.getSaldo()+player.getBet());
                        }
                        if(botcounter>counter1)
                        {
                          resultado.setText("Vencedor! Recebe -"+player.getBet()+"$");
                          resultado.setVisible(true);
                          player.setSaldo(player.getSaldo()+2*player.getBet());
                        }
                        if(botcounter<counter1)
                        {
                          resultado.setText("Perdedor! Tenta outra vez (Dica, aposta mais)!");
                          resultado.setVisible(true);
                          if(player.getBet()>=player.getSaldo())
                          {
                            player.setBet(player.getSaldo());
                            cabeçalho.bettingarea.setText("bet value: "+player.getBet()+"$");
                          }
                        }
                      }
                      if(botscore>score2)
                      {
                        resultado.setText("Perdedor! Tenta outra vez (Dica, aposta mais)!");
                        resultado.setVisible(true);
                         if(player.getBet()>=player.getSaldo())
                          {
                            player.setBet(player.getSaldo());
                            cabeçalho.bettingarea.setText("bet value: "+player.getBet()+"$");
                          }
                      }
                    }
                    if(botscore==21)
                    {
                      if(botscore==score2)
                      {
                        if(botcounter<counter1)
                        {
                          resultado.setText("Perdedor! Tenta outra vez (Dica, aposta mais)!");
                          resultado.setVisible(true);
                          if(player.getBet()>=player.getSaldo())
                          {
                            player.setBet(player.getSaldo());
                            cabeçalho.bettingarea.setText("bet value: "+player.getBet()+"$");
                          }
                        }
                        if(botcounter==counter1)
                        {
                          resultado.setText("Empate! A sua aposta foi retornada!");
                          resultado.setVisible(true);
                          player.setSaldo(player.getSaldo()+player.getBet());
                        }
                        if(botcounter>counter1)
                        {
                          resultado.setText("Vencedor! Recebe -"+player.getBet()+"$");
                          resultado.setVisible(true);
                          player.setSaldo(player.getSaldo()+2*player.getBet());
                        }
                      }
                      else
                      {
                          resultado.setText("Perdedor! Tenta outra vez (Dica, aposta mais)!");
                          resultado.setVisible(true);
                          if(player.getBet()>=player.getSaldo())
                          {
                            player.setBet(player.getSaldo());
                            cabeçalho.bettingarea.setText("bet value: "+player.getBet()+"$");
                          }
                      }
                    }
                    if(botscore>21)
                    {
                      resultado.setText("Bot Bust! Vencedor! Recebe -"+player.getBet()+"$");
                      resultado.setVisible(true);
                      player.setSaldo(player.getSaldo()+2*player.getBet());
                    }
                    cabeçalho.saldo.setText("Saldo:"+player.getSaldo()+"$");
                    computerhand.setText("Cartas computador: Score :"+botscore);
                    segundamao=0;
            	}
            }
          }

        });
        hit.addMouseListener(new MouseAdapter() { 
          public void mouseClicked(MouseEvent e){
                if (score2==0)
                {
                  if (score<21 && counter<3) 
                  {
                     counter++;
                        player.setBlackcards(baralho.drawFromDeck(),counter+1);
                        score=getCardsScore(player.getBlackcards(counter+1),score);
                        if(player.hasAce(player.getBlackcards(counter+1))==true)
                        {
                            acecounter++;
                         } 
                       if(score>21)
                       {
                           if(acecounter>0)
                            {

                             while(acecounter>0)
                              {
                                score=score-10;
                                acecounter--;
                              }
                            }
                        }
                        scoremao.setText("Score="+score);
                        tuamao.add(player.getBlackcards(counter+1).imgcard);
                        if(score>21)
                        {
                          hit.setVisible(false);
                          stand.setVisible(false);
                          split.setVisible(false);
                          resultado.setText("Bust!");
                          resultado.setVisible(true);
                          label[0].setVisible(false);
                          bot.add(computer.getBlackcards(1).imgcard);
                          computerhand.setText("Cartas computador: Score :"+botscore);
                        }
                        if(score==21)
                        {
                          ganhou.setVisible(true);
                        }
                    }
                }
                else
                {
	              if(segundamao==0)	
	              {
	                  if (score<21 && counter<3) 
	                  {
	                     
	                    counter++;
	                    player.setBlackcards(baralho.drawFromDeck(),counter+3);  
	                    score=getCardsScore(player.getBlackcards(counter+3),score);
	                    if(player.hasAce(player.getBlackcards(counter+3))==true)
	                    {
	                        acecounter++;
	                     } 
	                     if(score>21)
	                     {
	                         if(acecounter>0)
	                          {

	                           while(acecounter>0)
	                            {
	                              score=score-10;
	                              acecounter--;
	                            }
	                          }
	                      }
	                    scoremao.setText("Score1="+score);
	                    tuamao.add(player.getBlackcards(counter+3).imgcard);
	                    if(score>21)
	                    {
	                      resultado.setText("Bust! Clique para jogar a segunda mao!");
	                      resultado.setVisible(true);
	                      hit.setVisible(false);
	                      stand.setVisible(false);
	                      split.setVisible(false);
	                      segunda.setVisible(true);
	                    }
	              	}
	              }
	              else
	              {
	              	if (score2<21 && counter1<3) 
	                {
	                     
	                    counter1++;
	                    player.setBlackcards(baralho.drawFromDeck(),counter+3+counter1);  
	                    score2=getCardsScore(player.getBlackcards(counter+3+counter1),score2);
	                    if(player.hasAce(player.getBlackcards(counter+3+counter1))==true)
	                    {
	                        acecounter++;
	                     } 
	                     if(score2>21)
	                     {
	                         if(acecounter>0)
	                          {

	                           while(acecounter>0)
	                            {
	                              score2=score2-10;
	                              acecounter--;
	                            }
	                          }
	                      }
	                    scoremao.setText("Score2="+score2);
	                    tuamao.add(player.getBlackcards(counter+3+counter1).imgcard);
	                    if(score2>21)
	                    {
	                      resultado.setText("Bust! Tenta novamente!");
	                      resultado.setVisible(true);
	                      hit.setVisible(false);
	                      stand.setVisible(false);
	                      split.setVisible(false);
	              			segundamao=0;
	                    }
	                  
	                 }
                }
            }
           }
          });
  
  cenas.add(cabeçalho);
  headerbot.add(computerhand);
  cenas.add(musica);
   cenas.add(headerbot);
   tuamao.add(label[2]);
   tuamao.add(label[3]);
   bot.add(label[0]);
   bot.add(label[1]);
   cenas.add(bot);
   mao = new JTextArea("As suas cartas:");
   Cartscor.add(mao);
   Cartscor.add(scoremao);
   cenas.add(Cartscor);
   action.add(hit);
   action.add(stand);
   action.add(split);
   action.add(resultado);
    action.add(segunda);
   cenas.add(tuamao);
   cenas.add(action);
   add(cenas);
  }
  public int getCardsScore(Card a,int score)
  {
    switch(a.getRank())
    {
      case 1:
      return score+2;
      case 2:
      return score+3;
      case 3:
      return score+4;
      case 4:
      return score+5;
      case 5:
      return score+6;
      case 6:
      return score+7;
      case 7:
      return score+8;
      case 8:
      return score+9;
      case 9:
      return score+10;
      case 10:
      return score+10;
      case 11:
      return score+10;
      case 12:
      return score+10;
      case 0:
      {
        return score+11;
      }
    }
    return score+0;
  }
  public static void main(String[] args) {
    new Blackjack().setVisible(true);
  }
}
