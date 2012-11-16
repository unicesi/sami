package co.edu.unicesi.sami.client.home;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicesi.sami.bo.ObjetivoEspecificoBO;
import co.edu.unicesi.sami.bo.RecursoAsignadoBO;
import co.edu.unicesi.sami.bo.SesionBO;
import co.edu.unicesi.sami.bo.TrabajoAsignadoBO;
import co.edu.unicesi.sami.bo.UnidadBO;
import co.edu.unicesi.sami.client.controller.DTEvent;
import co.edu.unicesi.sami.client.home.dialogos.DialogoGestionSesion;
import co.edu.unicesi.sami.client.home.dialogos.DialogoEditarSesion;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;
import co.edu.unicesi.sami.client.listados.ListadosService;
import co.edu.unicesi.sami.client.listados.ListadosServiceAsync;
import co.edu.unicesi.sami.client.model.ObjetivoEspecificoModel;
import co.edu.unicesi.sami.client.model.RecursoModel;
import co.edu.unicesi.sami.client.model.SesionModel;
import co.edu.unicesi.sami.client.model.TrabajoAsignadoModel;
import co.edu.unicesi.sami.client.model.UnidadModel;
import co.edu.unicesi.sami.client.planificador.PlanificadorService;
import co.edu.unicesi.sami.client.planificador.PlanificadorServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
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
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TabPlanificador extends TabItem {
	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);
	private final PlanificadorServiceAsync planificadorService = GWT
			.create(PlanificadorService.class);
	private final ListadosServiceAsync listadosService = GWT
			.create(ListadosService.class);

	private Text labUnidadesSeleccionar;
	private Text labUnidadesSeleccionadas;
	private Text labObjEspecificos;
	private Grid<ObjetivoEspecificoModel> gridObjEspecificos;
	private Grid<SesionModel> gridSesiones;
	private Grid<UnidadModel> gridUnidad;
	private Grid<UnidadModel> gridUnidadSeleccionada;
	private Button btnAgregar;
	private Button btnVerTodos;
	private Button btnAdicionar;
	private Button btnRemover;
	private DialogoGestionSesion dialogoAgregarSesion;
	private DialogoEditarSesion dialogoEditarSesion;
	private int idSesion;
	private UnidadModel unidadGridUnidad = null;
	private UnidadModel unidadGridUnidadSeleccionada = null;

	public TabPlanificador() {

		setText(MultiLingualConstants.tabPlanificador_text());
		setSize(800, 600);
		LayoutContainer container = new LayoutContainer();
		container.setLayout(new AbsoluteLayout());

		ListStore<UnidadModel> listaUnidades = new ListStore<UnidadModel>();
		gridUnidad = new Grid<UnidadModel>(listaUnidades,
				getColumnModelUnidad());
		gridUnidad.setBorders(true);
		gridUnidad.setStripeRows(true);
		gridUnidad.setSize("250px", "150px");

		ContentPanel cpUnidad = new ContentPanel();
		cpUnidad.setBodyBorder(false);
		cpUnidad.setHeading(MultiLingualConstants
				.dualListUnidadesSeleccionar_text());
		cpUnidad.setButtonAlign(HorizontalAlignment.CENTER);
		cpUnidad.setLayout(new FitLayout());
		cpUnidad.setSize(250, 150);
		cpUnidad.add(gridUnidad);
		container.add(cpUnidad, new AbsoluteData(100, 30));

		gridUnidadSeleccionada = new Grid<UnidadModel>(
				new ListStore<UnidadModel>(), getColumnModelUnidad());
		gridUnidadSeleccionada.setBorders(true);
		gridUnidadSeleccionada.setStripeRows(true);
		gridUnidadSeleccionada.setSize("250px", "150px");

		ContentPanel cpUnidadSeleccionada = new ContentPanel();
		cpUnidadSeleccionada.setBodyBorder(false);
		cpUnidadSeleccionada.setHeading(MultiLingualConstants
				.dualListUnidadesSeleccionadas_text());
		cpUnidadSeleccionada.setButtonAlign(HorizontalAlignment.CENTER);
		cpUnidadSeleccionada.setLayout(new FitLayout());
		cpUnidadSeleccionada.setSize(250, 150);
		cpUnidadSeleccionada.add(gridUnidadSeleccionada);
		container.add(cpUnidadSeleccionada, new AbsoluteData(400, 30));

		gridObjEspecificos = new Grid<ObjetivoEspecificoModel>(
				new ListStore<ObjetivoEspecificoModel>(),
				getColumnModelObjEspecificos());
		gridObjEspecificos.setBorders(true);
		gridObjEspecificos.setStripeRows(true);
		gridObjEspecificos.setSize(200, 125);

		ContentPanel cpObjs = new ContentPanel();
		cpObjs.setBodyBorder(false);
		cpObjs.setHeading(MultiLingualConstants.tableObjEspecificos_heading());
		cpObjs.setButtonAlign(HorizontalAlignment.CENTER);
		cpObjs.setLayout(new FitLayout());
		cpObjs.setSize(200, 125);
		cpObjs.add(gridObjEspecificos);
		container.add(cpObjs, new AbsoluteData(300, 220));

		gridSesiones = new Grid<SesionModel>(new ListStore<SesionModel>(),
				getColumnModelSesiones());
		gridSesiones.setBorders(true);
		gridSesiones.setStripeRows(true);
		gridSesiones.setSize("600px", "125px");

		ContentPanel cpSes = new ContentPanel();
		cpSes.setBodyBorder(false);
		cpSes.setHeading(MultiLingualConstants.tableSesiones_heading());
		cpSes.setButtonAlign(HorizontalAlignment.CENTER);
		cpSes.setLayout(new FitLayout());
		cpSes.setSize(600, 125);
		cpSes.add(gridSesiones);
		container.add(cpSes, new AbsoluteData(100, 400));

		btnAgregar = new Button(MultiLingualConstants.btnAgregar_text());
		container.add(btnAgregar, new AbsoluteData(400, 550));

		btnVerTodos = new Button(MultiLingualConstants.btnVerTodos_text());
		container.add(btnVerTodos, new AbsoluteData(325, 550));

		btnAdicionar = new Button(">");
		container.add(btnAdicionar, new AbsoluteData(365, 55));

		btnRemover = new Button("<");
		container.add(btnRemover, new AbsoluteData(365, 100));
		btnRemover.setSize("18px", "22px");

		add(container);
		inicializarDialogos();
		eventoCargarTab();
		eventoAgregarSesion();
		eventoEditarSesion();
		eventoAdicionarUnidad();
		eventoRemoverUnidad();
		eventoCambioSeleccionGridUnidad();
		eventoCambioSeleccionGridUnidadSeleccionada();
	}

	public void inicializarDialogos() {
		dialogoAgregarSesion = new DialogoGestionSesion(this);
	}

	private void eventoCargarTab() {
		this.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				cargarUnidadesDisponibles();
			}
		});
	}

	private void eventoAgregarSesion() {
		btnAgregar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				dialogoAgregarSesion.cargarSesiones(unidadGridUnidadSeleccionada.getId());
				dialogoAgregarSesion.cargarRecursos();
				dialogoAgregarSesion.show();
			}
		});
	}

	private void eventoEditarSesion() {

	}

	private void eventoAdicionarUnidad() {
		btnAdicionar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				adicionarUnidad();
			}
		});
	}

	private void eventoRemoverUnidad() {
		btnRemover.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				removerUnidad();
			}
		});
	}

	private void eventoCambioSeleccionGridUnidad() {
		gridUnidad.getSelectionModel().addSelectionChangedListener(
				new SelectionChangedListener<UnidadModel>() {
					@Override
					public void selectionChanged(
							SelectionChangedEvent<UnidadModel> se) {
						unidadGridUnidad = se.getSelectedItem();
						cargarSesiones();
					}
				});
	}

	private void eventoCambioSeleccionGridUnidadSeleccionada() {
		gridUnidadSeleccionada.getSelectionModel().addSelectionChangedListener(
				new SelectionChangedListener<UnidadModel>() {
					@Override
					public void selectionChanged(
							SelectionChangedEvent<UnidadModel> se) {
						unidadGridUnidadSeleccionada = se.getSelectedItem();
						cargarObjetivosEspecificos();
						cargarSesiones();
					}
				});
	}

	private void adicionarUnidad() {
		if (unidadGridUnidad == null) {
			Info.display("Error", "Debe Seleccionar la Unidad");
		} else {
			gridUnidadSeleccionada.getStore().add(unidadGridUnidad);
			gridUnidad.getStore().remove(unidadGridUnidad);
			unidadGridUnidad = null;
		}

	}

	private void removerUnidad() {
		if (unidadGridUnidadSeleccionada == null) {
			Info.display("Error", "Debe Seleccionar la Unidad");
		} else {
			gridUnidad.getStore().add(unidadGridUnidadSeleccionada);
			gridUnidadSeleccionada.getStore().remove(
					unidadGridUnidadSeleccionada);
			unidadGridUnidadSeleccionada = null;
		}
	}

	private void cargarUnidadesDisponibles() {
		String codigoCurso = Registry.get("codigoCurso");
		listadosService.listarUnidadesPorCurso(codigoCurso,
				new AsyncCallback<List<UnidadBO>>() {
					@Override
					public void onSuccess(List<UnidadBO> result) {
						Dispatcher.forwardEvent(
								DTEvent.LISTAR_UNIDADES_PLANIFICADOR, result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}
				});

	}

	private void cargarObjetivosEspecificos() {
		listadosService.listarObjEspecificosPorUnidad(
				unidadGridUnidadSeleccionada.getId(),
				new AsyncCallback<List<ObjetivoEspecificoBO>>() {
					@Override
					public void onSuccess(List<ObjetivoEspecificoBO> result) {
						Dispatcher.forwardEvent(
								DTEvent.LISTAR_OBJ_ESPECIFICOS_PLANIFICADOR,
								result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}

				});
	}

	private void cargarSesiones() {
		listadosService.listarSesionesPorUnidad(
				unidadGridUnidadSeleccionada.getId(),
				new AsyncCallback<List<SesionBO>>() {
					@Override
					public void onSuccess(List<SesionBO> result) {
						Dispatcher
								.forwardEvent(DTEvent.LISTAR_SESIONES, result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}

				});

	}

	private ColumnModel getColumnModelObjEspecificos() {
		List<ColumnConfig> configsObj = new ArrayList<ColumnConfig>();

		ColumnConfig columnObj = new ColumnConfig("nombre",
				MultiLingualConstants.columnObjEspecificos_nombre(), 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsObj.add(columnObj);

		columnObj = new ColumnConfig("contenido",
				MultiLingualConstants.columnObjEspecificos_contenido(), 200);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsObj.add(columnObj);

		return new ColumnModel(configsObj);
	}

	private ColumnModel getColumnModelSesiones() {
		List<ColumnConfig> configsSesiones = new ArrayList<ColumnConfig>();

		ColumnConfig columnObj = new ColumnConfig("numero",
				MultiLingualConstants.columnObjEspecificos_nombre(), 50);
		columnObj.setAlignment(HorizontalAlignment.CENTER);
		configsSesiones.add(columnObj);

		columnObj = new ColumnConfig("nombre",
				MultiLingualConstants.columnObjEspecificos_contenido(), 100);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsSesiones.add(columnObj);

		return new ColumnModel(configsSesiones);
	}

	private ColumnModel getColumnModelUnidad() {
		List<ColumnConfig> configsUnidades = new ArrayList<ColumnConfig>();

		ColumnConfig columnObj = new ColumnConfig("numero",
				MultiLingualConstants.columnUnidades_numero(), 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);

		columnObj = new ColumnConfig("nombre",
				MultiLingualConstants.columnUnidades_nombre(), 100);
		columnObj.setAlignment(HorizontalAlignment.CENTER);
		configsUnidades.add(columnObj);

		columnObj = new ColumnConfig("contenido",
				MultiLingualConstants.columnUnidades_contenido(), 450);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);

		return new ColumnModel(configsUnidades);
	}

	private native String getTemplate() /*-{
										return [
										'<tpl for=".">',
										'<div class="x-combo-list-item" qtip="{id}" qtitle="Id:">{nombre}</div>',
										'</tpl>' ].join("");
										}-*/;

	public void actualizarTablaUnidadesDisponibles(
			ListStore<UnidadModel> objModel) {
		this.gridUnidad.reconfigure(objModel, getColumnModelUnidad());
	}

	public void actualizarTablaObjetivosEspecificos(
			ListStore<ObjetivoEspecificoModel> objModel) {
		this.gridObjEspecificos.reconfigure(objModel,
				getColumnModelObjEspecificos());
	}

	public void actualizarTablaSesiones(ListStore<SesionModel> objModel) {
		this.gridSesiones.reconfigure(objModel, getColumnModelSesiones());

	}

	public void actualizarTablaRecursos(ListStore<RecursoModel> objModel) {
		// TODO Auto-generated method stub
		dialogoAgregarSesion.actualizarTablaRecursos(objModel);
	}

	public void agregarSesion(int numSesion, String descripcion) {

		SesionBO sesion = new SesionBO();
		sesion.setIdUnidad(unidadGridUnidadSeleccionada.getId());
		sesion.setNombre("Sesion" + numSesion);
		sesion.setNumero(numSesion);
		planificadorService.agregarSesion(sesion, new AsyncCallback<Integer>() {
			@Override
			public void onSuccess(Integer result) {
				cargarSesiones();
				Info.display("sami", Mensajero.mostrarMensaje(result));
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", Mensajero.ON_FAILURE);
			}
		});

	}

	public void agregarTrabajoAsignado(int idSesion2, String encargado,
			String contenido, String tipo) {
		TrabajoAsignadoBO trabajo = new TrabajoAsignadoBO();
		trabajo.setIdSesion(idSesion2);
		trabajo.setContenido(contenido);
		trabajo.setTipo(tipo);
		trabajo.setEncargado(encargado);
		planificadorService.agregarTrabajoAsignado(trabajo,
				new AsyncCallback<Integer>() {
					@Override
					public void onSuccess(Integer result) {
						cargarSesiones();
						Info.display("sami", Mensajero.mostrarMensaje(result));
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}
				});

	}

	public void agregarRecursosAsignados(int idTrabajoAsignado,
			ListStore<RecursoModel> recursos) {
		for (int i = 0; i < recursos.getCount(); i++) {
			RecursoAsignadoBO recursoAsignado = new RecursoAsignadoBO();
			recursoAsignado.setIdTrabajoAsignado(idTrabajoAsignado);
			recursoAsignado.setIdRecurso(recursos.getAt(i).getId());
			planificadorService.agregarRecursoAsignado(recursoAsignado,
					new AsyncCallback<Integer>() {
						@Override
						public void onSuccess(Integer result) {
							cargarSesiones();
							Info.display("sami",
									Mensajero.mostrarMensaje(result));
						}

						@Override
						public void onFailure(Throwable caught) {
							Info.display("Error", Mensajero.ON_FAILURE);
						}
					});

		}

	}

	public void actualizarTablaTrabajos(ListStore<TrabajoAsignadoModel> objModel) {
		dialogoAgregarSesion.actualizarTablaTrabajosAsignados(objModel);		
	}
	
	public void actualizarTablaSesionesDialogo(ListStore<SesionModel> objModel) {
		dialogoAgregarSesion.actualizarTablaSesiones(objModel);		
	}
}