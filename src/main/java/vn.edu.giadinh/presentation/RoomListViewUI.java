package vn.edu.giadinh.presentation;

import vn.edu.giadinh.buiness.RoomViewDTO;
import vn.edu.giadinh.buiness.TimKiemPhongUseCase;
import vn.edu.giadinh.buiness.TinhTongPhongUseCase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

/**
 * RoomListViewUI - Presentation layer class for displaying room information
 * Implements Observer pattern for UI updates and follows MVP/MVVM architecture
 */
public class RoomListViewUI extends JFrame {
    // UI Components
    private JTextField searchField;
    private JButton searchButton;
    private JButton statisticsButton;
    private JTable roomTable;
    private DefaultTableModel tableModel;
    private JTextArea messageArea;
    
    // Use Cases (Business Layer Dependencies)
    private TimKiemPhongUseCase timKiemPhongUseCase;
    private TinhTongPhongUseCase tinhTongPhongUseCase;
    
    // Table column names
    private static final String[] COLUMN_NAMES = {
        "Mã Phòng", "Dãy Nhà", "Loại Phòng", "Trạng Thái"
    };

    public RoomListViewUI(TimKiemPhongUseCase timKiemPhongUseCase, 
                         TinhTongPhongUseCase tinhTongPhongUseCase) {
        this.timKiemPhongUseCase = timKiemPhongUseCase;
        this.tinhTongPhongUseCase = tinhTongPhongUseCase;
        
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
        
        // Create table model and table
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        
        roomTable = new JTable(tableModel);
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomTable.getTableHeader().setBackground(new Color(70, 130, 180));
        roomTable.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(roomTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    /**
     * Create message panel for displaying notifications
     */
    private JPanel createMessagePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Thông Báo"));
        
        messageArea = new JTextArea(3, 0);
        messageArea.setEditable(false);
        messageArea.setBackground(new Color(245, 245, 245));
        
        JScrollPane scrollPane = new JScrollPane(messageArea);
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
     * Display list of rooms in the table
     * @param roomViewModels List of RoomViewDTO to display
     */
    public void showList(List<RoomViewDTO> roomViewModels) {
        // Clear existing data
        tableModel.setRowCount(0);
        
        // Add new data
        for (RoomViewDTO room : roomViewModels) {
            Object[] rowData = {
                room.getMaPhong(),
                room.getDayNha(),
                room.getType(),
                room.getTrangThai()
            };
            tableModel.addRow(rowData);
        }
        
        // Update message
        hienThiThongBao("Hiển thị " + roomViewModels.size() + " phòng.");
    }

    /**
     * Handle search button click event
     * @param keyword Search keyword
     */
    public void onTimKiemButton(String keyword) {
        try {
            if (keyword.isEmpty()) {
                hienThiThongBao("Vui lòng nhập mã phòng!");
                return;
            }
            
            hienThiThongBao("Đang tìm kiếm...");
            
            // Execute search use case
            List<RoomViewDTO> results = timKiemPhongUseCase.execute(keyword);
            
            // Display results
            showList(results);
            
            if (results.isEmpty()) {
                hienThiThongBao("Không tìm thấy phòng nào với mã phòng: " + keyword);
            } else {
                hienThiThongBao("Tìm thấy " + results.size() + " phòng với mã phòng: " + keyword);
            }
            
        } catch (Exception e) {
            hienThiThongBao("Lỗi khi tìm kiếm: " + e.getMessage());
        }
    }

    /**
     * Handle statistics button click event
     */
    public void onThongKeButton() {
        try {
            hienThiThongBao("Đang tính toán thống kê...");
            
            // Execute statistics use case
            Map<String, Integer> statistics = tinhTongPhongUseCase.execute();
            
            // Display statistics in a dialog
            showStatisticsDialog(statistics);
            
            hienThiThongBao("Thống kê đã được hiển thị.");
            
        } catch (Exception e) {
            hienThiThongBao("Lỗi khi tính thống kê: " + e.getMessage());
        }
    }

    /**
     * Display statistics in a dialog window
     * @param statistics Map containing statistics data
     */
    private void showStatisticsDialog(Map<String, Integer> statistics) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== THỐNG KÊ PHÒNG HỌC ===\n\n");
        
        sb.append("Tổng số phòng: ").append(statistics.get("Tổng số phòng")).append("\n\n");
        
        sb.append("Phân loại theo loại phòng:\n");
        sb.append("- Phòng Lý Thuyết: ").append(statistics.get("Phòng Lý Thuyết")).append("\n");
        sb.append("- Phòng Máy Tính: ").append(statistics.get("Phòng Máy Tính")).append("\n");
        sb.append("- Phòng Thí Nghiệm: ").append(statistics.get("Phòng Thí Nghiệm")).append("\n\n");
        
        sb.append("Phân loại theo tiêu chuẩn:\n");
        sb.append("- Phòng đạt chuẩn: ").append(statistics.get("Phòng đạt chuẩn")).append("\n");
        sb.append("- Phòng không đạt chuẩn: ").append(statistics.get("Phòng không đạt chuẩn")).append("\n");
        
        JOptionPane.showMessageDialog(this, sb.toString(), "Thống Kê Phòng Học", 
                                    JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Display notification message
     * @param message Message to display
     */
    public void hienThiThongBao(String message) {
        messageArea.append("[" + java.time.LocalTime.now().toString().substring(0, 8) + "] " + message + "\n");
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }

    /**
     * Update the UI (Observer pattern implementation)
     */
    public void update() {
        // Refresh the current view
        repaint();
        revalidate();
    }
}
