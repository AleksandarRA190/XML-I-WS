package ftn.xmlws.AdminModule.view.mainFrame.comments;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ftn.xmlws.AdminModule.Comment;
import ftn.xmlws.AdminModule.Singleton;
import ftn.xmlws.AdminModule.User;
import ftn.xmlws.AdminModule.controller.actions.UserActionConfirmComment;
import ftn.xmlws.AdminModule.controller.actions.UserActionDeleteComment;

public class CommentsFrame extends JDialog {

	private static final long serialVersionUID = 1L;

	private List<Comment> comments;

	private JPanel mainPanel;
	private JPanel bottomPanel;

	private JList<Comment> commentsList;
	private DefaultListModel<Comment> commentsListModel;
	private JScrollPane scrollPane;

	private Comment selectedComment;

	private JButton approveCommentButton;
	private JButton deleteCommentButton;

	private JButton closeButton;

	public CommentsFrame(List<Comment> comments, User user) {
		this.comments = comments;
		this.selectedComment = new Comment();

		this.initFrame();
	}

	public void initFrame() {
		this.setTitle("Comments");
		this.setPreferredSize(new Dimension(400, 300));
		this.setIconImage(Singleton.getInstance().getInitIcons().getAppImage());
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.initPanels();
	}

	public void initPanels() {
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BorderLayout());

		this.bottomPanel = new JPanel();
		this.bottomPanel.setLayout(new FlowLayout());

		this.initComponents();
		this.commentsList.getVisibleRowCount();
		this.scrollPane = new JScrollPane(this.commentsList);
		this.mainPanel.add(this.scrollPane, BorderLayout.CENTER);

		this.add(this.mainPanel, BorderLayout.CENTER);

		this.bottomPanel.add(this.approveCommentButton);
		this.bottomPanel.add(this.deleteCommentButton);
		this.bottomPanel.add(this.closeButton);

		this.add(this.bottomPanel, BorderLayout.SOUTH);

	}

	public void initComponents() {
		this.commentsList = new JList<Comment>();
		this.commentsListModel = new DefaultListModel<Comment>();
		this.commentsList.setModel(this.commentsListModel);
		this.commentsList.setCellRenderer(new CommentsListRenderer());
		this.initModel();
		this.commentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.approveCommentButton = new JButton("Approve");
		this.deleteCommentButton = new JButton("Decline");
		this.commentsList.setSelectionBackground(Color.red);
		this.commentsList.setSelectionForeground(Color.red);

		this.commentsList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (commentsList.getSelectedIndex() != -1) {
					selectedComment = comments.get(commentsList.getSelectedIndex());
					approveCommentButton.setEnabled(true);
					deleteCommentButton.setEnabled(true);
					System.out.println("SELECTED: " + selectedComment.getContentOfComment());
				} else {
					approveCommentButton.setEnabled(false);
					deleteCommentButton.setEnabled(false);
				}
			}
		});

		this.closeButton = new JButton("Close");

		this.initActionListeners();

	}

	public void initActionListeners() {
		this.approveCommentButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserActionConfirmComment confirmComment = (UserActionConfirmComment) Singleton.getInstance()
						.getContext().getBean(UserActionConfirmComment.class);
				confirmComment.setComment(selectedComment);
				try {
					confirmComment.action();

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error while approving comments. Please try again later.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}

				if (confirmComment.isSuccess()) {
					JOptionPane.showMessageDialog(null,
							"You have successfully confirmed a comment: /r/n" + selectedComment.getContentOfComment(),
							"Success", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});

		this.deleteCommentButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserActionDeleteComment deleteComment = (UserActionDeleteComment) Singleton.getInstance().getContext()
						.getBean(UserActionDeleteComment.class);
				deleteComment.setComment(selectedComment);
				try {
					deleteComment.action();

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error while deleting comments. Please try again later.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}

				if (deleteComment.isSuccess()) {
					JOptionPane.showMessageDialog(null,
							"You have successfully deleted a comment: /r/n" + selectedComment.getContentOfComment(),
							"Success", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});

		this.closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public void initModel() {
		for (int i = 0; i < this.comments.size(); i++) {
			this.commentsListModel.add(i, comments.get(i));
		}
		this.commentsList.setVisibleRowCount(comments.size());
	}

}
