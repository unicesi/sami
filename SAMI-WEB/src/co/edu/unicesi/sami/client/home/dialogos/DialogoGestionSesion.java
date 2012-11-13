package co.edu.unicesi.sami.client.home.dialogos;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicesi.sami.bo.RecursoBO;
import co.edu.unicesi.sami.bo.TrabajoAsignadoBO;
import co.edu.unicesi.sami.bo.SesionBO;
import co.edu.unicesi.sami.client.controller.DTEvent;
import co.edu.unicesi.sami.client.home.Mensajero;
import co.edu.unicesi.sami.client.home.TabPlanificador;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;
import co.edu.unicesi.sami.client.listados.ListadosService;
import co.edu.unicesi.sami.client.listados.ListadosServiceAsync;
import co.edu.unicesi.sami.client.model.RecursoModel;
import co.edu.unicesi.sami.client.model.SesionModel;
import co.edu.unicesi.sami.client.model.TrabajoAsignadoModel;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class DialogoGestionSesion extends Dialog {

	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);

	private final ListadosServiceAsync listadosService = GWT
			.create(ListadosService.class);

	private Grid<RecursoModel> gridRecurso;
	private Grid<RecursoModel> gridRecursoSeleccionado;
	private Grid<SesionModel> gridSesiones;
	private Grid<TrabajoAsignadoModel> gridTrabajoAsignado;
	private TabPlanificador tabPlanificador;
	private DialogoAgregarSesion dialogAgregarSesion;
	private DialogoAgregarTrabajoAsignado dialogAgregarTrabajoAsignado;

	private Button btnAdicionar;
	private Button btnRemover;

	private Button btnAgregarSesion;
	private Button btnAgregarTrabajoAsignado;

	private RecursoModel recursoGridRecurso;
	private RecursoModel recursoGridRecursoSeleccionado;

	private int idSesion = 0;
	private int idTrabajoAsignado = 0;

	public DialogoGestionSesion(TabPlanificador tabPlanificador) {
		setModal(true);
		setHeading(MultiLingualConstants.dialogoAgregarSesion_heading());
		setLayout(new AbsoluteLayout());
		setSize(500, 500);
		this.tabPlanificador = tabPlanificador;

		ListStore<SesionModel> listaUnidades = new ListStore<SesionModel>();
		gridSesiones = new Grid<SesionModel>(listaUnidades,
				getColumnModelSesion());
		gridSesiones.setBorders(true);
		gridSesiones.setStripeRows(true);
		gridSesiones.setSize("250px", "125px");

		ContentPanel cpUnidad = new ContentPanel();
		cpUnidad.setBodyBorder(false);
		cpUnidad.setHeading("Sesiones disponibles");
		cpUnidad.setButtonAlign(HorizontalAlignment.CENTER);
		cpUnidad.setLayout(new FitLayout());
		cpUnidad.setSize(250, 125);
		cpUnidad.add(gridSesiones);
		add(cpUnidad, new AbsoluteData(100, 30));

		ListStore<TrabajoAsignadoModel> list = new ListStore<TrabajoAsignadoModel>();
		gridTrabajoAsignado = new Grid<TrabajoAsignadoModel>(list,
				getColumnModelTrabajoAsignado());
		gridTrabajoAsignado.setBorders(true);
		gridTrabajoAsignado.setStripeRows(true);
		gridTrabajoAsignado.setSize("250px", "125px");

		ContentPanel cpTrabajoAsignado = new ContentPanel();
		cpTrabajoAsignado.setBodyBorder(false);
		cpTrabajoAsignado.setHeading("Trabajo Asignado");
		cpTrabajoAsignado.setButtonAlign(HorizontalAlignment.CENTER);
		cpTrabajoAsignado.setLayout(new FitLayout());
		cpTrabajoAsignado.setSize(250, 125);
		cpTrabajoAsignado.add(gridTrabajoAsignado);
		add(cpTrabajoAsignado, new AbsoluteData(100, 170));

		gridRecurso = new Grid<RecursoModel>(new ListStore<RecursoModel>(),
				getColumnModelMaterial());
		add(gridRecurso, new AbsoluteData(100, 328));
		gridRecurso.setSize("100px", "100px");

		gridRecursoSeleccionado = new Grid<RecursoModel>(
				new ListStore<RecursoModel>(), getColumnModelMaterial());
		add(gridRecursoSeleccionado, new AbsoluteData(252, 328));
		gridRecursoSeleccionado.setSize("100px", "100px");

		Label lblDisponibles = new Label("Recursos Disponibles");
		add(lblDisponibles, new AbsoluteData(88, 306));

		Label lblSeleccionados = new Label("Recursos Seleccionados");
		add(lblSeleccionados, new AbsoluteData(266, 306));

		btnAdicionar = new Button(">");
		add(btnAdicionar, new AbsoluteData(210, 340));

		btnRemover = new Button("<");
		add(btnRemover, new AbsoluteData(210, 378));
		btnRemover.setSize("18px", "22px");

		btnAgregarSesion = new Button(MultiLingualConstants.btnAgregar_text());
		add(btnAgregarSesion, new AbsoluteData(370, 80));

		btnAgregarTrabajoAsignado = new Button(
				MultiLingualConstants.btnAgregar_text());
		add(btnAgregarTrabajoAsignado, new AbsoluteData(370, 220));

		inicializarDialogos();
		eventoAdicionarRecurso();
		eventoRemoverRecurso();
		eventoCambioSeleccionGridUnidad();
		eventoCambioSeleccionGridUnidadSeleccionada();
		eventoAgregarSesion();
		eventoAgregarTrabajoAsignado();
		eventoCerrarVentana();
		eventoGestionarSesion();
		eventoSeleccionSesion();
		eventoSeleccionTrabajoAsignado();
	}

	private void inicializarDialogos() {

		dialogAgregarSesion = new DialogoAgregarSesion(this);
		dialogAgregarTrabajoAsignado = new DialogoAgregarTrabajoAsignado(this);
	}

	private void eventoAgregarSesion() {
		btnAgregarSesion
				.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						dialogAgregarSesion.show();
					}
				});
	}

	private void eventoAgregarTrabajoAsignado() {
		btnAgregarTrabajoAsignado
				.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						if (idSesion != 0) {
							dialogAgregarTrabajoAsignado.show();
						} else {
							Info.display("Error", "Debe seleccionar una sesión");
						}
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

	private void eventoGestionarSesion() {
		getButtonById(OK).addSelectionListener(
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						if (idTrabajoAsignado != 0) {
							agregarRecursosAsignados();
						}
						limpiarDatos();
					}

				});

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

	public void cargarSesiones(int idUnidad) {
		listadosService.listarSesionesPorUnidad(idUnidad,
				new AsyncCallback<List<SesionBO>>() {
					@Override
					public void onFailure(Throwable arg0) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}

					@Override
					public void onSuccess(List<SesionBO> result) {
						Dispatcher.forwardEvent(
								DTEvent.LISTAR_SESIONES_DIALOGO_GESTION_SESION,
								result);

					}

				});
	}

	private void cargarTrabajosSeleccionados() {
		listadosService.listarTrabajosAsignadosPorSesion(idSesion,
				new AsyncCallback<List<TrabajoAsignadoBO>>() {
					@Override
					public void onFailure(Throwable arg0) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}

					@Override
					public void onSuccess(List<TrabajoAsignadoBO> result) {
						Dispatcher.forwardEvent(
								DTEvent.LISTAR_TRABAJOS_DIALOGO_GESTION_SESION,
								result);

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

	private void eventoSeleccionSesion() {
		gridSesiones.addListener(Events.CellClick,
				new Listener<GridEvent<SesionModel>>() {
					@Override
					public void handleEvent(GridEvent<SesionModel> e) {
						SesionModel model = e.getGrid().getSelectionModel()
								.getSelectedItem();
						idSesion = model.getId();
						cargarTrabajosSeleccionados();
					}
				});
	}

	private void eventoSeleccionTrabajoAsignado() {
		gridTrabajoAsignado.addListener(Events.CellClick,
				new Listener<GridEvent<TrabajoAsignadoModel>>() {
					@Override
					public void handleEvent(GridEvent<TrabajoAsignadoModel> e) {
						TrabajoAsignadoModel model = e.getGrid()
								.getSelectionModel().getSelectedItem();
						idTrabajoAsignado = model.getId();
					}
				});
	}

	private void adicionarUnidad() {
		if (recursoGridRecurso == null) {
			Info.display("Error", "Debe Seleccionar la Unidad");
		} else {
			gridRecursoSeleccionado.getStore().add(recursoGridRecurso);
			gridRecursoSeleccionado.getStore().remove(recursoGridRecurso);
			recursoGridRecurso = null;
		}

	}

	private void removerUnidad() {
		if (recursoGridRecursoSeleccionado == null) {
			Info.display("Error", "Debe Seleccionar la Unidad");
		} else {
			gridRecursoSeleccionado.getStore().add(
					recursoGridRecursoSeleccionado);
			gridRecursoSeleccionado.getStore().remove(
					recursoGridRecursoSeleccionado);
			recursoGridRecursoSeleccionado = null;
		}
	}

	private void agregarRecursosAsignados() {
		ListStore<RecursoModel> recursos = gridRecursoSeleccionado.getStore();
		tabPlanificador.agregarRecursosAsignados(idTrabajoAsignado, recursos);

	}

	private ColumnModel getColumnModelSesion() {
		List<ColumnConfig> configsUnidades = new ArrayList<ColumnConfig>();
		ColumnConfig columnObj = new ColumnConfig("numero",
				"Número", 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);

		columnObj = new ColumnConfig("nombre",
				"Nombre", 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);

		return new ColumnModel(configsUnidades);
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

		columnObj = new ColumnConfig("ruta",
				MultiLingualConstants.columnRecurso_ruta(), 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);

		return new ColumnModel(configsUnidades);
	}

	private ColumnModel getColumnModelTrabajoAsignado() {
		List<ColumnConfig> configsUnidades = new ArrayList<ColumnConfig>();
		ColumnConfig columnObj = new ColumnConfig("contenido",
				"Contenido", 100);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);

		columnObj = new ColumnConfig("encargado",
				"Encargado", 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);
		
		columnObj = new ColumnConfig("tipo",
				"Tipo", 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);
		
		

		return new ColumnModel(configsUnidades);
	}

	public void agregarSesion(String nombre, int numero) {
		tabPlanificador.agregarSesion(numero, nombre);
	}

	private void limpiarDatos() {
		hide();
	}

	public void actualizarTablaRecursos(ListStore<RecursoModel> objModel) {
		// TODO Auto-generated method stub
		gridRecurso.reconfigure(objModel, getColumnModelMaterial());
	}

	public void actualizarTablaSesiones(ListStore<SesionModel> objModel) {
		// TODO Auto-generated method stub
		gridSesiones.reconfigure(objModel, getColumnModelSesion());
	}

	public void actualizarTablaTrabajosAsignados(
			ListStore<TrabajoAsignadoModel> objModel) {
		// TODO Auto-generated method stub
		gridTrabajoAsignado.reconfigure(objModel, getColumnModelTrabajoAsignado());
	}

	public void actualizarTablaRecursosSeleccionados(
			ListStore<RecursoModel> objModel) {
		// TODO Auto-generated method stub
		gridRecursoSeleccionado.reconfigure(objModel, getColumnModelMaterial());
	}

	public void agregarTrabajoAsignado(String encargado, String tipo,
			String contenido) {

		tabPlanificador.agregarTrabajoAsignado(idSesion, encargado, contenido,
				tipo);

	}

}