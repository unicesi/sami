package co.edu.unicesi.sami.client.home.dialogos;

import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;
import co.edu.unicesi.sami.client.model.SesionModel;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.google.gwt.core.client.GWT;

public class DialogoEditarSesion extends Dialog {

	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);

	private Text labNombre;
	private Text labNumero;
	private TextField<String> txtNombre;
	private TextField<String> txtNumero;
	private DialogoGestionSesion dialogSesion;

	public DialogoEditarSesion(DialogoGestionSesion dialogSesion) {
		setModal(true);
		setHeading(MultiLingualConstants.dialogoAgregarUnidad_heading());
		setLayout(new AbsoluteLayout());
		setSize(500, 300);
		this.dialogSesion = dialogSesion;

		txtNombre = new TextField<String>();
		add(txtNombre, new AbsoluteData(157, 25));
		txtNombre.setSize("212px", "24px");

		txtNumero = new TextField<String>();
		add(txtNumero, new AbsoluteData(157, 68));
		txtNumero.setSize("212px", "24px");

		labNombre = new Text(MultiLingualConstants.labNombre_text());
		add(labNombre, new AbsoluteData(30, 36));

		labNumero = new Text(MultiLingualConstants.labNumero_text());
		add(labNumero, new AbsoluteData(30, 79));

		eventoAgregarUnidad();
		eventoCerrarVentana();
	}

	private void eventoAgregarUnidad() {
		getButtonById(OK).addSelectionListener(
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						agregarSesion();
						limpiarDatos();
					}
				});
	}

	private void eventoCerrarVentana() {
		this.addListener(Events.Close, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				limpiarDatos();
			}
		});
	}

	private void agregarSesion() {
		String nombre = txtNombre.getValue();
		int numero = Integer.parseInt(txtNumero.getValue());
		dialogSesion.agregarSesion(nombre, numero);
	}

	private void limpiarDatos() {
		txtNombre.clear();
		txtNumero.clear();
		hide();
	}
	
	public void cargarDatos(SesionModel sesion){
		
	}
}