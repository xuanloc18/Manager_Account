/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import controller.SearchProduct;
import controller.WritePDF;
import dao.AccountDAO;
import dao.ChiTietPhieuNhapDAO;
import dao.MayTinhDAO;
import dao.NhaCungCapDAO;
import dao.PhieuNhapDAO;
import dao.PhieuXuatDAO;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.Account;
import model.ChiTietPhieu;
import model.MayTinh;
import model.NhaCungCap;
import model.Phieu;
import model.PhieuNhap;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Tran Nhat Sinh
 */
public class NhapHangForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form NhapHang
     */
    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    private ArrayList<MayTinh> allProduct;
    private String MaPhieu;
    private ArrayList<ChiTietPhieu> CTPhieu;
    private static final ArrayList<NhaCungCap> arrNcc = NhaCungCapDAO.getInstance().selectAll();

    public NhapHangForm() {
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        initComponents();
        allProduct = MayTinhDAO.getInstance().selectAllExist();
        initTable();
        loadDataToTableProduct(allProduct);
        loadNccToComboBox();
        tblSanPham.setDefaultEditor(Object.class, null);
        tblNhapHang.setDefaultEditor(Object.class, null);
        MaPhieu = createId(PhieuNhapDAO.getInstance().selectAll());
        txtMaPhieu.setText(MaPhieu);
        CTPhieu = new ArrayList<ChiTietPhieu>();
    }

    private void loadNccToComboBox() {
        for (NhaCungCap i : arrNcc) {
            cboNhaCungCap.addItem(i.getTenNhaCungCap());
        }
    }

    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{"Mã máy", "Tên máy", "Số lượng", "Đơn giá"};
        tblModel.setColumnIdentifiers(headerTbl);
        tblSanPham.setModel(tblModel);
        tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblNhapHang.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblNhapHang.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblNhapHang.getColumnModel().getColumn(2).setPreferredWidth(250);
    }

    private void loadDataToTableProduct(ArrayList<MayTinh> arrProd) {
        try {
            tblModel.setRowCount(0);
            for (var i : arrProd) {
                tblModel.addRow(new Object[]{
                    i.getMaMay(), i.getTenMay(), i.getSoLuong(), formatter.format(i.getGia()) + "đ"
                });
            }
        } catch (Exception e) {
        }
    }

    public double tinhTongTien() {
        double tt = 0;
        for (var i : CTPhieu) {
            tt += i.getDonGia() * i.getSoLuong();
        }
        return tt;
    }

    public MayTinh findMayTinh(String maMay) {
        for (var i : allProduct) {
            if (maMay.equals(i.getMaMay())) {
                return i;
            }
        }
        return null;
    }

    public ChiTietPhieu findCTPhieu(String maMay) {
        for (var i : CTPhieu) {
            if (maMay.equals(i.getMaMay())) {
                return i;
            }
        }
        return null;
    }

    public void loadDataToTableNhapHang() {
        double sum = 0;
        try {
            DefaultTableModel tblNhapHangmd = (DefaultTableModel) tblNhapHang.getModel();
            tblNhapHangmd.setRowCount(0);

            for (int i = 0; i < CTPhieu.size(); i++) {
                tblNhapHangmd.addRow(new Object[]{
                    i + 1, CTPhieu.get(i).getMaMay(), findMayTinh(CTPhieu.get(i).getMaMay()).getTenMay(), CTPhieu.get(i).getSoLuong(), formatter.format(CTPhieu.get(i).getDonGia()) + "đ"
                });
                sum += CTPhieu.get(i).getDonGia() * CTPhieu.get(i).getSoLuong();
            }
        } catch (Exception e) {
        }
        textTongTien.setText(formatter.format(sum) + "đ");
    }

    public void setNguoiNhapHang(String name) {
        txtNguoiTao.setText(name);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaPhieu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtNguoiTao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhapHang = new javax.swing.JTable();
        btnNhapHang = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        textTongTien = new javax.swing.JLabel();
        deleteProduct = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        deleteProduct1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        addProduct = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Mã phiếu nhập");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        txtMaPhieu.setEditable(false);
        txtMaPhieu.setEnabled(false);
        txtMaPhieu.setFocusable(false);
        jPanel2.add(txtMaPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 390, 36));

        jLabel2.setText("Nhà cung cấp");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jPanel2.add(cboNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 390, 36));

        jLabel3.setText("Người tạo phiếu");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        txtNguoiTao.setEditable(false);
        jPanel2.add(txtNguoiTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 390, 36));

        tblNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng", "Đơn giá"
            }
        ));
        jScrollPane1.setViewportView(tblNhapHang);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 580, 400));

        btnNhapHang.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        btnNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapHang.setText("Nhập hàng");
        btnNhapHang.setBorder(null);
        btnNhapHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangActionPerformed(evt);
            }
        });
        jPanel2.add(btnNhapHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 690, 123, 37));

        jLabel5.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel5.setText("Tổng tiền:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 690, 120, 30));

        textTongTien.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        textTongTien.setForeground(new java.awt.Color(255, 0, 0));
        textTongTien.setText("0đ");
        jPanel2.add(textTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 690, 190, 30));

        deleteProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_delete_25px_1.png"))); // NOI18N
        deleteProduct.setText("Xoá sản phẩm ");
        deleteProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProductActionPerformed(evt);
            }
        });
        jPanel2.add(deleteProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 610, 160, 40));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_edit_25px.png"))); // NOI18N
        jButton1.setText("Sửa số lượng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 610, -1, 40));

        deleteProduct1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-microsoft-excel-2019-25.png"))); // NOI18N
        deleteProduct1.setText("Nhập excel");
        deleteProduct1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteProduct1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProduct1ActionPerformed(evt);
            }
        });
        jPanel2.add(deleteProduct1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 610, -1, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 620, 750));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã máy", "Tên máy", "Số lượng", "Đơn giá"
            }
        ));
        jScrollPane2.setViewportView(tblSanPham);

        jLabel4.setText("Số lượng");

        txtSoLuong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSoLuong.setText("1");

        addProduct.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        addProduct.setForeground(new java.awt.Color(255, 255, 255));
        addProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_add_25px_5.png"))); // NOI18N
        addProduct.setText("Thêm");
        addProduct.setBorder(null);
        addProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reset_25px_1.png"))); // NOI18N
        btnReset.setText("Làm mới");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel4)
                .addGap(27, 27, 27)
                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 750));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapHangActionPerformed
        // TODO add your handling code here:
        if (CTPhieu.size() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm để nhập hàng !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn nhập hàng ?", "Xác nhận nhập hàng", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                // Lay thoi gian hien tai
                long now = System.currentTimeMillis();
                Timestamp sqlTimestamp = new Timestamp(now);
                // Tao doi tuong phieu nhap
                PhieuNhap pn = new PhieuNhap(arrNcc.get(cboNhaCungCap.getSelectedIndex()).getMaNhaCungCap(), MaPhieu, sqlTimestamp, txtNguoiTao.getText(), CTPhieu, tinhTongTien());
                try {
                    PhieuNhapDAO.getInstance().insert(pn);
                    MayTinhDAO mtdao = MayTinhDAO.getInstance();
                    for (var i : CTPhieu) {
                        ChiTietPhieuNhapDAO.getInstance().insert(i);
                        mtdao.updateSoLuong(i.getMaMay(), mtdao.selectById(i.getMaMay()).getSoLuong() + i.getSoLuong());
                    }
                    JOptionPane.showMessageDialog(this, "Nhập hàng thành công !");
                    int res = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất file pdf ?","",JOptionPane.YES_NO_OPTION);
                    if (res == JOptionPane.YES_OPTION) {
                        WritePDF writepdf = new WritePDF();
                        writepdf.writePhieuNhap(MaPhieu);
                    }
                    ArrayList<MayTinh> productUp = MayTinhDAO.getInstance().selectAllExist();
                    txtSoLuong.setText("1");
                    loadDataToTableProduct(productUp);
                    DefaultTableModel r = (DefaultTableModel) tblNhapHang.getModel();
                    r.setRowCount(0);
                    CTPhieu = new ArrayList<>();
                    textTongTien.setText("0");
                    this.MaPhieu = createId(PhieuNhapDAO.getInstance().selectAll());
                    txtMaPhieu.setText(this.MaPhieu);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi !");
                }
            }
        }
    }//GEN-LAST:event_btnNhapHangActionPerformed

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed
        // TODO add your handling code here:
        int i_row = tblSanPham.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để nhập hàng !");
        } else {
            int soluong;
            try {
                soluong = Integer.parseInt(txtSoLuong.getText().trim());
                if (soluong > 0) {
                    System.out.println("sinh");
                    ChiTietPhieu mtl = findCTPhieu((String) tblSanPham.getValueAt(i_row, 0));
                    if (mtl != null) {
                        mtl.setSoLuong(mtl.getSoLuong() + soluong);
                    } else {
                        MayTinh mt = SearchProduct.getInstance().searchId((String) tblSanPham.getValueAt(i_row, 0));
                        ChiTietPhieu ctp = new ChiTietPhieu(MaPhieu, mt.getMaMay(), soluong, mt.getGia());
                        CTPhieu.add(ctp);
                    }
                    loadDataToTableNhapHang();
                    textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ở dạng số nguyên!");
            }
        }
    }//GEN-LAST:event_addProductActionPerformed

    private void deleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProductActionPerformed
        // TODO add your handling code here:
        int i_row = tblNhapHang.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xoá khỏi bảng nhập hàng !");
        } else {
            CTPhieu.remove(i_row);
            loadDataToTableNhapHang();
            textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
        }
    }//GEN-LAST:event_deleteProductActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int i_row = tblNhapHang.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xoá sửa số lượng !");
        } else {
            String newSL = JOptionPane.showInputDialog(this, "Nhập số lượng cần thay đổi", "Thay đổi số lượng", QUESTION_MESSAGE);
            if (newSL != null) {
                int soLuong;
                try {
                    soLuong = Integer.parseInt(newSL);
                    if (soLuong > 0) {
                        CTPhieu.get(i_row).setSoLuong(soLuong);
                        loadDataToTableNhapHang();
                        textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
                    } else {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ở dạng số nguyên!");
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        DefaultTableModel tblsp = (DefaultTableModel) tblSanPham.getModel();
        String textSearch = txtSearch.getText().toLowerCase();
        ArrayList<MayTinh> Mtkq = new ArrayList<>();
        for (MayTinh i : allProduct) {
            if (i.getMaMay().concat(i.getTenMay()).toLowerCase().contains(textSearch)) {
                Mtkq.add(i);
            }
        }
        loadDataToTableProduct(Mtkq);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtSearch.setText("");
        loadDataToTableProduct(allProduct);
    }//GEN-LAST:event_btnResetActionPerformed

    private void deleteProduct1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProduct1ActionPerformed
        // TODO add your handling code here:
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJTableImport = null;
        ArrayList<ChiTietPhieu> listAccExcel = new ArrayList<ChiTietPhieu>();
        JFileChooser jf = new JFileChooser();
        int result = jf.showOpenDialog(null);
        jf.setDialogTitle("Open file");
        Workbook workbook = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = jf.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelJTableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
                for (int row = 1; row < excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);
                    String maPhieu = txtMaPhieu.getText();
                    String maSanPham = excelRow.getCell(1).getStringCellValue();
                    String tenSanPham = excelRow.getCell(2).getStringCellValue();
                    int soLuong = (int) (excelRow.getCell(3).getNumericCellValue());
                
                    double donGia = MayTinhDAO.getInstance().selectById(maSanPham).getGia();
                    ChiTietPhieu ctpnew = new ChiTietPhieu(maPhieu, maSanPham, soLuong, donGia);
                    CTPhieu.add(ctpnew);
                }
                loadDataToTableNhapHang();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AccountForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AccountForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        loadDataToTableNhapHang();
    }//GEN-LAST:event_deleteProduct1ActionPerformed

    public String createId(ArrayList<PhieuNhap> arr) {
        int id = arr.size() + 1;
        String check = "";
        for (PhieuNhap phieuNhap : arr) {
            if (phieuNhap.getMaPhieu().equals("PN" + id)) {
                check = phieuNhap.getMaPhieu();
            }
        }
        while (check.length() != 0) {
            String c = check;
            id++;
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).getMaPhieu().equals("PN" + id)) {
                    check = arr.get(i).getMaPhieu();
                }
            }
            if (check.equals(c)) {
                check = "";
            }
        }
        return "PN" + id;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProduct;
    private javax.swing.JButton btnNhapHang;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JButton deleteProduct;
    private javax.swing.JButton deleteProduct1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblNhapHang;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JLabel textTongTien;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
