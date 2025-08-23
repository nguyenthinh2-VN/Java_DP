package vn.edu.giadinh.presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * RoomListViewUI - Presentation layer class for displaying room information
 * Implements Observer pattern for UI updates and follows MVP/MVVM architecture
 * Now properly depends on Controller and ViewModel instead of Use Cases directly
 */
public class RoomListViewUI extends JFrame implements Subscriber {
    // UI Components
    private JTextField searchField;
    private JButton searchButton;
    private JButton statisticsButton;
    private JTable roomTable;
    private DefaultTableModel tableModel;
    private JTextArea messageArea;
    
    // Controller and ViewModel (following the architecture diagram)
    private RoomViewController controller;
    private RoomViewModel viewModel;
    
    // Table column names
    private static final String[] COLUMN_NAMES = {
        "Mã Phòng", "Dãy Nhà", "Loại Phòng", "Diện Tích", "Số Bóng Đèn", "Chi Tiết", "Trạng Thái"
    };

    public RoomListViewUI(RoomViewController controller, RoomViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        
        // Subscribe to ViewModel updates (Observer pattern)
        this.viewModel.addSubscriber(this);
        
        initializeUI();
        setupEventHandlers();
    }

    /**
     * Initialize the user interface components
     */
    private void initializeUI() {
        setTitle("Hệ Thống Quản Lý Phòng Học");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create search panel
        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);
        
        // Create table panel
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);
        
        // Create message panel
        JPanel messagePanel = createMessagePanel();
        add(messagePanel, BorderLayout.SOUTH);
        
        // Set window properties
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Create search panel with search field and buttons
     */
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Tìm Kiếm và Thống Kê"));
        
        // Search components
        panel.add(new JLabel("Mã phòng:"));
        searchField = new JTextField(20);
        panel.add(searchField);
        
        searchButton = new JButton("Tìm Kiếm");
        searchButton.setBackground(new Color(70, 130, 180));
        searchButton.setForeground(Color.WHITE);
        panel.add(searchButton);
        
        statisticsButton = new JButton("Thống Kê");
        statisticsButton.setBackground(new Color(60, 179, 113));
        statisticsButton.setForeground(Color.WHITE);
        panel.add(statisticsButton);
        
        return panel;
    }

    /**
     * Create table panel for displaying room list
     */
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Danh Sách Phòng"));
        
        // Create table model
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        
        // Create table
        roomTable = new JTable(tableModel);
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomTable.setRowHeight(25);
        roomTable.getTableHeader().setReorderingAllowed(false);
        
        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setPreferredSize(new Dimension(750, 300));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    /**
     * Create message panel for displaying notifications
     */
    private JPanel createMessagePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Thông Báo"));
        
        messageArea = new JTextArea(5, 50);
        messageArea.setEditable(false);
        messageArea.setBackground(new Color(245, 245, 245));
        messageArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    /**
     * Setup event handlers for buttons
     */
    private void setupEventHandlers() {
        // Search button event handler
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText().trim();
                onTimKiemButton(keyword);
            }
        });
        
        // Statistics button event handler
        statisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onThongKeButton();
            }
        });
        
        // Enter key support for search field
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText().trim();
                onTimKiemButton(keyword);
            }
        });
    }

    /**
     * Handle search button click event
     * Now uses Controller instead of Use Case directly
     * @param keyword Search keyword
     */
    public void onTimKiemButton(String keyword) {
        try {
            if (keyword.isEmpty()) {
                hienThiThongBao("Vui lòng nhập mã phòng!");
                return;
            }
            
            hienThiThongBao("Đang tìm kiếm...");
            
            // Use Controller instead of Use Case directly (following architecture)
            controller.timKiem(keyword);
            
            hienThiThongBao("Tìm kiếm hoàn tất với từ khóa: " + keyword);
            
        } catch (Exception e) {
            hienThiThongBao("Lỗi khi tìm kiếm: " + e.getMessage());
        }
    }

    /**
     * Handle statistics button click event
     * Now uses Controller instead of Use Case directly
     */
    public void onThongKeButton() {
        try {
            hienThiThongBao("Đang tính toán thống kê...");
            
            // Use Controller instead of Use Case directly (following architecture)
            String statisticsResult = controller.handleThongKe();
            
            // Display statistics in a dialog
            showStatisticsDialog(statisticsResult);
            
            hienThiThongBao("Thống kê đã được hiển thị.");
            
        } catch (Exception e) {
            hienThiThongBao("Lỗi khi tính thống kê: " + e.getMessage());
        }
    }

    /**
     * Display statistics in a dialog window
     * @param statisticsText Formatted statistics text
     */
    private void showStatisticsDialog(String statisticsText) {
        JOptionPane.showMessageDialog(
            this,
            statisticsText,
            "Thống Kê Phòng Học",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Display notification message
     * @param message Message to display
     */
    public void hienThiThongBao(String message) {
        SwingUtilities.invokeLater(() -> {
            String timestamp = java.time.LocalTime.now().toString().substring(0, 8);
            messageArea.append("[" + timestamp + "] " + message + "\n");
            messageArea.setCaretPosition(messageArea.getDocument().getLength());
        });
    }

    /**
     * Implementation of Subscriber interface
     * Called when the ViewModel notifies of changes (Observer pattern)
     */
    @Override
    public void update() {
        SwingUtilities.invokeLater(() -> {
            refreshRoomList();
        });
    }

    /**
     * Refresh the room list display from ViewModel data
     */
    private void refreshRoomList() {
        // Clear existing data
        tableModel.setRowCount(0);
        
        // Get data from ViewModel (following MVVM pattern)
        List<RoomViewItem> roomItems = viewModel.roomList;
        
        // Add new data to table - ViewItems already contain formatted strings
        for (RoomViewItem item : roomItems) {
            Object[] rowData = {
                item.getMaPhong(),
                item.getDayNha(),
                item.getLoaiPhong(),      // Already formatted: "Phòng Lý Thuyết"
                item.getDienTich(),       // Already formatted: "50.0 m²"
                item.getSoBongDen(),      // Already formatted: "6 bóng"
                item.getChiTietPhong(),   // Already formatted: "Máy chiếu: Có"
                item.getTrangThai()       // Already formatted: "Đạt chuẩn"
            };
            tableModel.addRow(rowData);
        }
        
        // Update message
        hienThiThongBao("Hiển thị " + roomItems.size() + " phòng.");
    }
}
