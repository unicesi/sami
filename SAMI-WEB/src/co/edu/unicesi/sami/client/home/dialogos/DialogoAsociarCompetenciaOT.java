package co.edu.unicesi.sami.client.home.dialogos;

import co.edu.unicesi.sami.client.home.TabObjTerminales;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;

public class DialogoAsociarCompetenciaOT extends Dialog {
	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);

	private TabObjTerminales tabObjTerminales;
	private TextField<String> txtTipo;

	public DialogoAsociarCompetenciaOT(TabObjTerminales tabObjTerminales) {
		setButtonAlign(HorizontalAlignment.CENTER);
		this.tabObjTerminales = tabObjTerminales;

		setModal(true);
		setSize(220, 116);
		setHeading("Asociar competencia");
		setLayout(new AbsoluteLayout());

		Label lblTipo = new Label(
				"Tipo");
		add(lblTipo, new AbsoluteData(6, 6));
		
		txtTipo = new TextField<String>();

		add(txtTipo, new AbsoluteData(32, 6));
		
		eventoAsociarCompetencia();
		eventoCerrarVentana();
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
			ensena="0";
			aplica="0";
		}else if(txtTipo.getValue().equalsIgnoreCase("enseña")){
			introduce="0";
			ensena=txtTipo.getValue();
			aplica="0";
		}else{
			introduce="0";
			ensena="0";
			aplica=txtTipo.getValue();}
	tabObjTerminales.asociarCompetencia(introduce,ensena,aplica);
	
	}

	private void limpiarDatos() {
		txtTipo.clear();
		hide();
	}
	
}

