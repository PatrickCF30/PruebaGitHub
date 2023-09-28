package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCodigo;
	JComboBox cboCategorias;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JComboBox cboProveedores;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		txtSalida.setFont(new Font("Consolas", Font.BOLD, 13));
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 102, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(249, 106, 55, 14);
		contentPane.add(lblProveedor);
		
		cboProveedores = new JComboBox();
		cboProveedores.setBounds(311, 102, 102, 22);
		contentPane.add(cboProveedores);
		
		 
		llenaComboCat();
		llenaComboProv();
	}
    void llenaComboProv() {
		// 1. obtener la conexion --> llamar a la unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		// 2. crear un manejador de entidades segun la conexion
	    EntityManager em = fabrica.createEntityManager();
		
		// select * from tb_proveedores ----> colección --> List
        String jpql = "SELECT p FROM Proveedor p"; 
		List<Proveedor> lstProveedores = em.createQuery(jpql, Proveedor.class).
				getResultList();	
	
		// mostrar el listado
		cboProveedores.addItem("Seleccionar");
		for (Proveedor p : lstProveedores) {
			cboProveedores.addItem(p.getNombre_rs());
		}
		em.close();
	}
	void llenaComboCat() {
		// 1. obtener la conexion --> llamar a la unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		// 2. crear un manejador de entidades segun la conexion
	    EntityManager em = fabrica.createEntityManager();
		
		// select * from tb_categorias ----> colección --> List
        String jpql = "SELECT c FROM Categoria c"; 
		List<Categoria> lstCategorias = em.createQuery(jpql, Categoria.class).
				getResultList();	
	
		// mostrar el listado
		cboCategorias.addItem("Seleccionar");
		for (Categoria c : lstCategorias) {
            cboCategorias.addItem(c.getDescripcion());
		}
		em.close();
	}
	
	void listado() {
		// Listado de los Productos
		// 1. obtener la conexion --> llamar a la unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		// 2. crear un manejador de entidades segun la conexion
	    EntityManager em = fabrica.createEntityManager();
		
		// select * from tb_productos ----> colección --> List
        String jpql = "SELECT p FROM Producto p"; 
		List<Producto> lstProductos = em.createQuery(jpql, Producto.class).
				getResultList();	
		
		// mostrar el listado
		txtSalida.setText("---------------------------------\n");	
		imprimir("------Listado de Productos-------");
		imprimir("---------------------------------");		
		for (Producto p : lstProductos) {
			imprimir("Código....: " + p.getId_prod());
			imprimir("Producto..: " + p.getDes_prod());
			imprimir("Stock.....: " + p.getStk_prod());
			imprimir("Precio....: " + p.getPre_prod());
			imprimir("Categoria.: " + p.getIdcategoria() + "-" + p.getObjCategoria().getDescripcion());
			imprimir("Estado....: " + p.getEst_prod());
			imprimir("Proveedor.: " + p.getObjProveedor().getNombre_rs() + "-" 
			                        + p.getObjProveedor().getTelefono());
			imprimir("---------------------------------");
		}
		em.close();
	}
	
	void registrar() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	    EntityManager em = fabrica.createEntityManager();
		
		Producto p = new Producto();
 		String codigo;	
 		
 		p.setId_prod(txtCodigo.getText());
 		p.setDes_prod(txtDescripcion.getText());
 		p.setStk_prod(Integer.parseInt(txtStock.getText()));
 		p.setPre_prod(Double.parseDouble(txtPrecio.getText()));
 		p.setIdcategoria(cboCategorias.getSelectedIndex());
 		p.setIdproveedor(cboProveedores.getSelectedIndex());
 		p.setEst_prod(1);
 		
 		try {
 			em.getTransaction().begin();
 			em.persist(p);
 			em.getTransaction().commit();
			aviso("Registro OK");
		} catch (Exception e) {
            aviso("Error: " + e.getCause().getMessage());
		}
		em.close();
	}
	void imprimir(String texto) {
		txtSalida.append(texto + "\n");
	}
	private void aviso(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
}
