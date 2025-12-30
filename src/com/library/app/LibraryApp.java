package com.library.app;

import com.library.dao.BookDAO;
import com.library.dao.MemberDAO;
import com.library.dao.BorrowDAO;
import com.library.model.Book;
import com.library.model.Member;
import com.library.model.BorrowRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.*;

public class LibraryApp extends JFrame {

    private final BookDAO bookDAO = new BookDAO();
    private final MemberDAO memberDAO = new MemberDAO();
    private final BorrowDAO borrowDAO = new BorrowDAO();

    private JTable bookTable, memberTable, borrowTable;
    private DefaultTableModel bookModel, memberModel, borrowModel;
    private TableRowSorter<DefaultTableModel> bookSorter, memberSorter, borrowSorter;

    public LibraryApp() {
        setTitle("Library Management System");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Books", booksPanel());
        tabs.add("Members", membersPanel());
        tabs.add("Borrow / Return", borrowPanel());

        add(tabs);

        loadBooks();
        loadMembers();
        loadBorrows();
    }

    // ================= BOOKS =================

    private JPanel booksPanel() {
        bookModel = new DefaultTableModel(
                new String[]{"ID", "Title", "Author", "Available"}, 0
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        bookTable = new JTable(bookModel);
        bookSorter = new TableRowSorter<>(bookModel);
        bookTable.setRowSorter(bookSorter);

        JTextField filter = new JTextField();
        addLiveFilter(filter, bookSorter);

        JPanel top = new JPanel(new BorderLayout(5, 5));
        top.add(new JLabel("Filter:"), BorderLayout.WEST);
        top.add(filter, BorderLayout.CENTER);

        JButton add = new JButton("Add");
        JButton edit = new JButton("Edit");
        JButton del = new JButton("Delete");

        add.addActionListener(e -> addBook());
        edit.addActionListener(e -> editBook());
        del.addActionListener(e -> deleteBook());

        JPanel bottom = new JPanel();
        bottom.add(add);
        bottom.add(edit);
        bottom.add(del);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(bookTable), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }

    private void loadBooks() {
        try {
            bookModel.setRowCount(0);
            for (Book b : bookDAO.getAll()) {
                bookModel.addRow(new Object[]{
                        b.getId(), b.getTitle(), b.getAuthor(), b.getAvailable()
                });
            }
        } catch (Exception e) {
            showError(e);
        }
    }

    private void addBook() {
        try {
            bookDAO.create(new Book(
                    0,
                    JOptionPane.showInputDialog("Title"),
                    JOptionPane.showInputDialog("Author"),
                    Integer.parseInt(JOptionPane.showInputDialog("Available copies"))
            ));
            loadBooks();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void editBook() {
        int row = bookTable.getSelectedRow();
        if (row < 0) return;
        try {
            int id = (int) bookTable.getValueAt(row, 0);
            Book b = bookDAO.findById(id);
            bookDAO.update(new Book(
                    id,
                    JOptionPane.showInputDialog("Title", b.getTitle()),
                    JOptionPane.showInputDialog("Author", b.getAuthor()),
                    Integer.parseInt(JOptionPane.showInputDialog("Available", b.getAvailable()))
            ));
            loadBooks();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void deleteBook() {
        int row = bookTable.getSelectedRow();
        if (row < 0) return;
        try {
            bookDAO.delete((int) bookTable.getValueAt(row, 0));
            loadBooks();
        } catch (Exception e) {
            showError(e);
        }
    }

    // ================= MEMBERS =================

    private JPanel membersPanel() {
        memberModel = new DefaultTableModel(
                new String[]{"ID", "Name", "Email", "Mobile"}, 0
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        memberTable = new JTable(memberModel);
        memberSorter = new TableRowSorter<>(memberModel);
        memberTable.setRowSorter(memberSorter);

        JTextField filter = new JTextField();
        addLiveFilter(filter, memberSorter);

        JPanel top = new JPanel(new BorderLayout(5, 5));
        top.add(new JLabel("Filter:"), BorderLayout.WEST);
        top.add(filter, BorderLayout.CENTER);

        JButton add = new JButton("Add");
        JButton edit = new JButton("Edit");
        JButton del = new JButton("Delete");

        add.addActionListener(e -> addMember());
        edit.addActionListener(e -> editMember());
        del.addActionListener(e -> deleteMember());

        JPanel bottom = new JPanel();
        bottom.add(add);
        bottom.add(edit);
        bottom.add(del);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(memberTable), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }

    private void loadMembers() {
        try {
            memberModel.setRowCount(0);
            for (Member m : memberDAO.getAll()) {
                memberModel.addRow(new Object[]{
                        m.getId(), m.getName(), m.getEmail(), m.getMobile()
                });
            }
        } catch (Exception e) {
            showError(e);
        }
    }

    private void addMember() {
        try {
            memberDAO.create(new Member(
                    0,
                    JOptionPane.showInputDialog("Name"),
                    JOptionPane.showInputDialog("Email"),
                    JOptionPane.showInputDialog("Mobile")
            ));
            loadMembers();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void editMember() {
        int row = memberTable.getSelectedRow();
        if (row < 0) return;
        try {
            int id = (int) memberTable.getValueAt(row, 0);
            Member m = memberDAO.findById(id);
            memberDAO.update(new Member(
                    id,
                    JOptionPane.showInputDialog("Name", m.getName()),
                    JOptionPane.showInputDialog("Email", m.getEmail()),
                    JOptionPane.showInputDialog("Mobile", m.getMobile())
            ));
            loadMembers();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void deleteMember() {
        int row = memberTable.getSelectedRow();
        if (row < 0) return;
        try {
            memberDAO.delete((int) memberTable.getValueAt(row, 0));
            loadMembers();
        } catch (Exception e) {
            showError(e);
        }
    }

    // ================= BORROW =================

    private JPanel borrowPanel() {
        borrowModel = new DefaultTableModel(
                new String[]{"ID", "Book ID", "Member ID", "Borrow Date", "Return Date", "Returned"}, 0
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        borrowTable = new JTable(borrowModel);
        borrowSorter = new TableRowSorter<>(borrowModel);
        borrowTable.setRowSorter(borrowSorter);

        JTextField filter = new JTextField();
        addLiveFilter(filter, borrowSorter);

        JPanel top = new JPanel(new BorderLayout(5, 5));
        top.add(new JLabel("Filter:"), BorderLayout.WEST);
        top.add(filter, BorderLayout.CENTER);

        JButton issue = new JButton("Issue");
        JButton ret = new JButton("Return");

        issue.addActionListener(e -> issueBook());
        ret.addActionListener(e -> returnBook());

        JPanel bottom = new JPanel();
        bottom.add(issue);
        bottom.add(ret);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(borrowTable), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }

    private void loadBorrows() {
        try {
            borrowModel.setRowCount(0);
            for (BorrowRecord b : borrowDAO.getAll()) {
                borrowModel.addRow(new Object[]{
                        b.getId(), b.getBookId(), b.getMemberId(),
                        b.getBorrowDate(), b.getReturnDate(), b.isReturned()
                });
            }
        } catch (Exception e) {
            showError(e);
        }
    }

    private void issueBook() {
        try {
            borrowDAO.issueBook(
                    Integer.parseInt(JOptionPane.showInputDialog("Book ID")),
                    Integer.parseInt(JOptionPane.showInputDialog("Member ID"))
            );
            loadBooks();
            loadBorrows();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void returnBook() {
        try {
            borrowDAO.returnBook(
                    Integer.parseInt(JOptionPane.showInputDialog("Borrow ID"))
            );
            loadBooks();
            loadBorrows();
        } catch (Exception e) {
            showError(e);
        }
    }

    // ================= FILTER =================

    private void addLiveFilter(JTextField field, TableRowSorter<?> sorter) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { apply(); }
            public void removeUpdate(DocumentEvent e) { apply(); }
            public void changedUpdate(DocumentEvent e) { apply(); }
            private void apply() {
                String text = field.getText();
                sorter.setRowFilter(
                        text.trim().isEmpty() ? null :
                                RowFilter.regexFilter("(?i)" + text)
                );
            }
        });
    }

    private void showError(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryApp().setVisible(true));
    }
}
