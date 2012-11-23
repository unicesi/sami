package co.edu.unicesi.sami.client.home.dialogos;

import co.edu.unicesi.sami.client.home.TabObjEspecificos;
import co.edu.unicesi.sami.client.home.TabObjTerminales;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;

public class DialogoAsociarCompetenciaOE extends Dialog {
	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);

	private TabObjEspecificos tabObjEspecificos;
	private TextField<String> txtTipo;

	public DialogoAsociarCompetenciaOE(TabObjEspecificos tabObjEspecificos) {
		setButtonAlign(HorizontalAlignment.CENTER);
		this.tabObjEspecificos = tabObjEspecificos;

		setModal(true);
		setSize(220, 116);
		setHeading("Asociar competencia");
		setLayout(new AbsoluteLayout());

		Label lblTipo = new Label(
				"Tipo");
		add(lblTipo, new AbsoluteData(6, 6));
		
		txtTipo = new TextField<String>();

		add(txtTipo, new AbsoluteData(32, 6));
		
	}
	
	private void eventoAsociarCompetencia() {
		getButtonById(OK).addSelectionListener(
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						asociarCompetencia();
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

	private void asociarCompetencia() {
		String introduce="";
		String ensena="";
		String aplica="";
		if(txtTipo.getValue().equalsIgnoreCase("introduce")){
			introduce=txtTipo.getValue();
			ensena="";
			aplica="";
		}else if(txtTipo.getValue().equalsIgnoreCase("enseña")){
			introduce="";
			ensena=txtTipo.getValue();
			aplica="";
		}else{
			introduce="";
			ensena="";
			aplica=txtTipo.getValue();}
	//tabObjEspecificos.asociarCompetencia(introduce,ensena,aplica);
	
	}

	private void limpiarDatos() {
		txtTipo.clear();
		hide();
	}
}
