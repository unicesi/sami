package co.edu.unicesi.sami.client.home.dialogos;

import co.edu.unicesi.sami.client.home.TabMateriales;
import co.edu.unicesi.sami.client.home.TabObjEspecificos;
import co.edu.unicesi.sami.client.home.TabObjTerminales;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;

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
import com.google.gwt.user.client.ui.ListBox;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;

public class DialogoAsociarCompetencia extends Dialog {
	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);

	private TabObjTerminales tabObjTerminales;
	private TabObjEspecificos tabObjEspecificos;
	private TextField<String> txtTipo;
	private Button btnAsociar;

	public DialogoAsociarCompetencia(TabObjTerminales tabObjTerminales) {
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
		
		btnAsociar = new Button("Asociar");
		add(btnAsociar,new AbsoluteData(92,200));
	}
	
	public DialogoAsociarCompetencia(TabObjEspecificos tabObjEspecificos) {
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
		
		btnAsociar = new Button("Asociar");
		add(btnAsociar,new AbsoluteData(92,200));
	}
	
}

