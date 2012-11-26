package co.edu.unicesi.sami.client.home.dialogos;
import co.edu.unicesi.sami.client.home.Mensajero;
import co.edu.unicesi.sami.client.home.TabUnidades;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.google.gwt.core.client.GWT;

public class DialogoAgregarTrabajoAsignado extends Dialog {

	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);

	private Text labEncargado;
	private Text labContenido;
	private Text labTipo;
	private TextField<String> txtEncargado;
	private TextField<String> txtTipo;
	private TextArea txtContenido;
	private TabUnidades tabUnidades;
	private DialogoGestionSesion dialogSesion;

	public DialogoAgregarTrabajoAsignado(DialogoGestionSesion dialogSesion) {
		setModal(true);
		setHeading(MultiLingualConstants.dialogoAgregarUnidad_heading());
		setLayout(new AbsoluteLayout());
        setSize( 500, 300 );
		this.dialogSesion = dialogSesion;

		txtEncargado = new TextField<String>();
		add(txtEncargado, new AbsoluteData(157, 25));
		txtEncargado.setSize("212px", "24px");

		txtTipo = new TextField<String>();
		add(txtTipo, new AbsoluteData(157, 68));
		txtTipo.setSize("212px", "24px");
		
		txtContenido = new TextArea();
		add(txtContenido, new AbsoluteData(157, 110));
		txtContenido.setSize("212px", "60px");

		labEncargado = new Text("Encargado");
		add(labEncargado, new AbsoluteData(30, 36));

		labContenido = new Text("Contenido");
		add(labContenido, new AbsoluteData(30, 110));

		labTipo = new Text("Tipo");
		add(labTipo, new AbsoluteData(30, 79));

		

		eventoAgregarTrabajoAsignado();
		eventoCerrarVentana();
	}

	private void eventoAgregarTrabajoAsignado() {
		getButtonById(OK).addSelectionListener(
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						if(agregarTrabajoAsignado() == 0)
						{
							
						}
						else
							Info.display("Error", Mensajero.mostrarMensaje(agregarTrabajoAsignado()));
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

	private int agregarTrabajoAsignado() {
		
		if(txtEncargado.getValue() == null || txtContenido.getValue() == null || txtTipo == null)
			return Mensajero.CAMPOSVACIOS;
		
		String encargado = txtEncargado.getValue();
		
		if(!encargado.equals("E") && !encargado.equals("P"))
			return Mensajero.ERRORFORMATO;
		
		String contenido = txtContenido.getValue();
		String tipo = txtTipo.getValue();
		
		if(!tipo.equalsIgnoreCase("ANTES") && 
				!tipo.equalsIgnoreCase("DURANTE") &&
				!tipo.equalsIgnoreCase("DESPUES"))
			return Mensajero.ERRORFORMATO;
		
		dialogSesion.agregarTrabajoAsignado(encargado, tipo, contenido);
		return Mensajero.AGREGAR;
	}

	private void limpiarDatos() {
		txtEncargado.clear();
		txtContenido.clear();
		txtTipo.clear();
		hide();
	}
}


