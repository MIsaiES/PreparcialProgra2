package umg.Form;
import org.jdesktop.swingx.JXDatePicker;
import umg.Model.Datos;
import umg.Services.DatosService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class FormularioDatos extends JFrame {
    private JPanel panelDatos;
    private JLabel labelNombre;
    private JLabel labelApellido;
    private JLabel labelDepartamento;
    private JLabel labelFechaNacimiento;
    private JXDatePicker datePickerFechaNacimiento;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldDepartamento;
    private JButton buttonCrear;
    private JButton buttonBuscar;
    private JButton buttonActualizar;
    private JButton buttonBorar;
    private JLabel labelid;
    private JTextField textFieldid;

    DatosService datosService = new DatosService();

    public FormularioDatos() {
        buttonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Datos datos = new Datos();
                datos.setNombre(textFieldNombre.getText());
                datos.setApellido(textFieldApellido.getText());
                datos.setDepartamento(textFieldDepartamento.getText());
                datos.setFechaNacimiento((Date) datePickerFechaNacimiento.getDate());

                datosService.agregarDatos(datos);
            }
        });
    }

    public void abrirFormulario() {
        JFrame frame = new JFrame("Formulario Datos");
        frame.setContentPane(new FormularioDatos().panelDatos);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
