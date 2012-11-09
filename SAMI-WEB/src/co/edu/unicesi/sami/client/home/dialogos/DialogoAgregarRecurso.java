package co.edu.unicesi.sami.client.home.dialogos;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicesi.sami.bo.RecursoBO;
import co.edu.unicesi.sami.client.controller.DTEvent;
import co.edu.unicesi.sami.client.home.Mensajero;
import co.edu.unicesi.sami.client.home.TabSaberes;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;
import co.edu.unicesi.sami.client.listados.ListadosService;
import co.edu.unicesi.sami.client.listados.ListadosServiceAsync;
import co.edu.unicesi.sami.client.model.RecursoModel;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DialogoAgregarRecurso extends Dialog {

	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);
	private final ListadosServiceAsync listadosService = GWT
			.create(ListadosService.class);

	private Grid<RecursoModel> gridRecurso;
	private Grid<RecursoModel> gridRecursoSeleccionado;
	private Button btnAdicionar;
	private Button btnRemover;

	private TabSaberes tabSaberes;
	private RecursoModel recursoGridRecurso;
	private RecursoModel recursoGridRecursoSeleccionado;

	public DialogoAgregarRecurso(TabSaberes tabSaberes) {
		setModal(true);
		setHeading(MultiLingualConstants.dialogoAgregarSesion_heading());
		setLayout(new AbsoluteLayout());
		setSize(500, 500);
		this.tabSaberes = tabSaberes;

		LayoutContainer container = new LayoutContainer();
		container.setLayout(new AbsoluteLayout());

		btnAdicionar = new Button(">");
		container.add(btnAdicionar, new AbsoluteData(193, 340));

		btnRemover = new Button("<");
		container.add(btnRemover, new AbsoluteData(193, 378));

		gridRecurso = new Grid<RecursoModel>(new ListStore<RecursoModel>(),
				getColumnModelMaterial());
		gridRecurso.setSize("100px", "100px");

		ContentPanel cpSaberes = new ContentPanel();
		cpSaberes.setBodyBorder(false);
		cpSaberes.setHeading(MultiLingualConstants.tableRecursos_heading());
		cpSaberes.setButtonAlign(HorizontalAlignment.CENTER);
		cpSaberes.setLayout(new FitLayout());
		cpSaberes.setSize(250, 150);
		cpSaberes.add(gridRecurso);
		container.add(cpSaberes, new AbsoluteData(100, 220));

		gridRecursoSeleccionado = new Grid<RecursoModel>(
				new ListStore<RecursoModel>(), getColumnModelMaterial());
		add(gridRecursoSeleccionado, new AbsoluteData(252, 328));
		gridRecursoSeleccionado.setSize("100px", "100px");

		ContentPanel cpSaberess = new ContentPanel();
		cpSaberess.setBodyBorder(false);
		cpSaberess.setHeading(MultiLingualConstants.tableRecursos_heading());
		cpSaberess.setButtonAlign(HorizontalAlignment.CENTER);
		cpSaberess.setLayout(new FitLayout());
		cpSaberess.setSize(250, 150);
		cpSaberess.add(gridRecursoSeleccionado);
		container.add(cpSaberess, new AbsoluteData(100, 220));
		
		eventoAdicionarRecurso();
		eventoCambioSeleccionGridUnidad();
		eventoCambioSeleccionGridUnidadSeleccionada();
		eventoRemoverRecurso();
		eventoCerrarVentana();eventoAgregarRecursos() ;

	}

	private void eventoCerrarVentana() {
		this.addListener(Events.Close, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				limpiarDatos();
			}
		});

	}

	private void eventoAgregarRecursos() {
		getButtonById(OK).addSelectionListener(
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						agregarRecursos();
						limpiarDatos();
					}
				});
	}

	private void eventoCambioSeleccionGridUnidad() {
		gridRecurso.getSelectionModel().addSelectionChangedListener(
				new SelectionChangedListener<RecursoModel>() {
					@Override
					public void selectionChanged(
							SelectionChangedEvent<RecursoModel> se) {
						recursoGridRecurso = se.getSelectedItem();
					}
				});
	}

	private void eventoCambioSeleccionGridUnidadSeleccionada() {
		gridRecursoSeleccionado.getSelectionModel()
				.addSelectionChangedListener(
						new SelectionChangedListener<RecursoModel>() {
							@Override
							public void selectionChanged(
									SelectionChangedEvent<RecursoModel> se) {
								recursoGridRecursoSeleccionado = se
										.getSelectedItem();
							}
						});
	}

	private void eventoAdicionarRecurso() {
		btnAdicionar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				adicionarUnidad();
			}
		});
	}

	private void eventoRemoverRecurso() {
		btnRemover.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				removerUnidad();
			}
		});
	}

	private void adicionarUnidad() {
		if (recursoGridRecurso == null) {
			Info.display("Error", "Debe Seleccionar el Recurso");
		} else {
			gridRecursoSeleccionado.getStore().add(recursoGridRecurso);
			gridRecursoSeleccionado.getStore().remove(recursoGridRecurso);
			recursoGridRecurso = null;
		}

	}

	private void removerUnidad() {
		if (recursoGridRecursoSeleccionado == null) {
			Info.display("Error", "Debe Seleccionar el Recurso");
		} else {
			gridRecursoSeleccionado.getStore().add(
					recursoGridRecursoSeleccionado);
			gridRecursoSeleccionado.getStore().remove(
					recursoGridRecursoSeleccionado);
			recursoGridRecursoSeleccionado = null;
		}
	}

	private void limpiarDatos() {
		gridRecurso.getStore().removeAll();
		gridRecursoSeleccionado.getStore().removeAll();
		hide();
	}

	private void agregarRecursos() {
		ListStore<RecursoModel> recursos = gridRecursoSeleccionado.getStore();
		tabSaberes.agregarRecursos(recursos);
	}

	public void cargarRecursos() {
		int idCurso = Registry.get("idCurso");
		listadosService.listarRecursosPorCurso(idCurso,
				new AsyncCallback<List<RecursoBO>>() {
					@Override
					public void onFailure(Throwable arg0) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}

					@Override
					public void onSuccess(List<RecursoBO> result) {
						Dispatcher.forwardEvent(
								DTEvent.LISTAR_RECURSOS_DIALOGO_AGREGAR_SESION,
								result);

					}

				});
	}

	private ColumnModel getColumnModelMaterial() {
		List<ColumnConfig> configsUnidades = new ArrayList<ColumnConfig>();

		ColumnConfig columnObj = new ColumnConfig("autor",
				MultiLingualConstants.columnRecurso_autor(), 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);

		columnObj = new ColumnConfig("titulo",
				MultiLingualConstants.columnRecurso_titulo(), 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);

		columnObj = new ColumnConfig("ano",
				MultiLingualConstants.columnRecurso_ano(), 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);

		columnObj = new ColumnConfig("ciudad",
				MultiLingualConstants.columnRecurso_ciudad(), 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);

		columnObj = new ColumnConfig("editorial",
				MultiLingualConstants.columnRecurso_editorial(), 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);

		return new ColumnModel(configsUnidades);
	}

}
