package co.edu.unicesi.sami.client.home.dialogos;

import co.edu.unicesi.sami.client.home.TabMateriales;
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
import com.google.gwt.user.client.ui.ListBox;

public class DialogoAgregarMaterial extends Dialog {
	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);

	private TabMateriales tabMateriales;
	private ListBox listFuente;
	private ListBox listIdioma;
	private TextField<String> txtAutor;
	private TextField<String> txtTitulo;
	private TextField<String> txtAno;
	private TextField<String> txtCiudad;
	private TextField<String> txtEditorial;

	public DialogoAgregarMaterial(TabMateriales tabMateriales) {
		this.tabMateriales = tabMateriales;

		setModal(true);
		setSize(500, 317);
		setHeading(MultiLingualConstants.dialogoAgregarMaterial_heading());
		setLayout(new AbsoluteLayout());

		Label lblTipoDeFuente = new Label(
				MultiLingualConstants.lblTipoDeFuente_text());
		add(lblTipoDeFuente, new AbsoluteData(55, 6));

		listFuente = new ListBox();
		listFuente.addItem("Libro ");
		listFuente.addItem("Revista");
		add(listFuente, new AbsoluteData(195, 6));
		listFuente.setVisibleItemCount(1);

		Label lblIdioma = new Label(MultiLingualConstants.lblIdioma_text());
		add(lblIdioma, new AbsoluteData(300, 6));

		listIdioma = new ListBox();
		listIdioma.addItem("Espa\u00F1ol");
		listIdioma.addItem("Ingl\u00E9s");
		add(listIdioma, new AbsoluteData(342, 6));
		listIdioma.setVisibleItemCount(1);

		Label lblAutor = new Label(MultiLingualConstants.columnRecurso_autor());
		add(lblAutor, new AbsoluteData(55, 50));

		Label lblTitulo = new Label(
				MultiLingualConstants.columnRecurso_titulo());
		add(lblTitulo, new AbsoluteData(55, 87));

		Label lblAo = new Label(MultiLingualConstants.columnRecurso_ano());
		add(lblAo, new AbsoluteData(63, 123));

		Label lblCiudad = new Label(
				MultiLingualConstants.columnRecurso_ciudad());
		add(lblCiudad, new AbsoluteData(49, 161));

		Label lblEditorial = new Label(
				MultiLingualConstants.columnRecurso_editorial());
		add(lblEditorial, new AbsoluteData(44, 201));

		txtAutor = new TextField<String>();

		add(txtAutor, new AbsoluteData(92, 50));

		txtTitulo = new TextField<String>();

		add(txtTitulo, new AbsoluteData(91, 87));

		txtAno = new TextField<String>();

		add(txtAno, new AbsoluteData(92, 123));

		txtCiudad = new TextField<String>();

		add(txtCiudad, new AbsoluteData(92, 161));

		txtEditorial = new TextField<String>();

		add(txtEditorial, new AbsoluteData(92, 201));

		eventoAgregarMaterial();
		eventoCerrarVentana();
	}

	private void eventoAgregarMaterial() {
		getButtonById(OK).addSelectionListener(
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						agregarMaterial();
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

	private void agregarMaterial() {
		String fuente = listFuente.getItemText(listFuente.getSelectedIndex());
		String idioma = listIdioma.getItemText(listIdioma.getSelectedIndex());
		String autor = txtAutor.getValue();
		String titulo = txtTitulo.getValue();
		String ano = txtAno.getValue();
		String ciudad = txtCiudad.getValue();
		String editorial = txtEditorial.getValue();
		tabMateriales.agregarMaterial(fuente + ";" + idioma + ";" + autor + ";"
				+ titulo + ";" + ano + ";" + ciudad + ";" + editorial);
	}

	private void limpiarDatos() {
		txtAutor.clear();
		txtTitulo.clear();
		txtAno.clear();
		txtCiudad.clear();
		txtEditorial.clear();
		hide();
	}
}