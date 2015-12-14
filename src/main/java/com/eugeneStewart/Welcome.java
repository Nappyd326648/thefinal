package com.eugeneStewart;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.eugeneStewart.database.*;

/**
 * Created by Eugene on 12/7/2015.
 */
public class Welcome extends JFrame {
    private JPanel rPlanel;
    private JButton btnFind;
    private JTextField txtLookfor;
    protected Welcome(){
        setContentPane(rPlanel);
        pack();
        setVisible(true);





        btnFind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               final String artist = txtLookfor.getText();
            Twitter twitter = CTwitter.Twitterlogon();
            try {
               ArrayList<String> tweets = CTwitter.Twittersearch(twitter, artist);
                JFrame.setDefaultLookAndFeelDecorated(true);
                final JFrame frame = new JFrame("New Tweets");
                frame.setLayout(new FlowLayout());
                JButton addButton = new JButton( "Save" );
                JButton doneButton = new JButton( "Done" );
                frame.add(addButton);
                frame. add(doneButton);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                final JList list = new JList(tweets.toArray());

                list.setSelectedIndex(0);
                frame.add(new JScrollPane(list));
                frame.pack();
                frame.setVisible(true);

                list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                addButton.addActionListener(
                        new ActionListener() {

                            public void actionPerformed(ActionEvent event) {
                                // prompt user for new philosopher's name

                                // add new philosopher to model
                                try {
                                    startdb(artist, list.getSelectedValue());
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                );
                doneButton.addActionListener(
                        new ActionListener() {

                            public void actionPerformed(ActionEvent event) {

                                try {
                                    database.onclose();
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                                frame.dispose();
                            }

                        }
                );


            } catch (TwitterException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }


        }
        });
    }


}
