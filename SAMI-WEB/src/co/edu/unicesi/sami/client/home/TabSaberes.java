package co.edu.unicesi.sami.client.home;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicesi.sami.bo.ObjetivoEspecificoBO;
import co.edu.unicesi.sami.bo.RecursoBO;
import co.edu.unicesi.sami.bo.SaberBO;
import co.edu.unicesi.sami.bo.UnidadBO;
import co.edu.unicesi.sami.client.controller.DTEvent;
import co.edu.unicesi.sami.client.home.dialogos.DialogoAgregarSaber;
import co.edu.unicesi.sami.client.home.dialogos.DialogoEditarSaber;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;
import co.edu.unicesi.sami.client.listados.ListadosService;
import co.edu.unicesi.sami.client.listados.ListadosServiceAsync;
import co.edu.unicesi.sami.client.model.ObjetivoEspecificoModel;
import co.edu.unicesi.sami.client.model.RecursoModel;
import co.edu.unicesi.sami.client.model.SaberModel;
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
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TabSaberes extends TabItem {

	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);
	private final PlanificadorServiceAsync planificadorService = GWT
			.create(PlanificadorService.class);
	private final ListadosServiceAsync listadosService = GWT
			.create(ListadosService.class);

	private DialogoAgregarSaber dialogoAgregarSaber;
	private DialogoEditarSaber dialogoEditarSaber;

	private int idObjEspecifico;
	private int idSaber;
	private int idUnidad;
	private Grid<SaberModel> gridSaberes;
	private Grid<UnidadModel> gridUnidad;
	private Grid<ObjetivoEspecificoModel> gridObjEspecificos;
	private Grid<RecursoModel> gridMateriales;
	private Button btnAgregar;

	public TabSaberes() {
		setText(MultiLingualConstants.tabSaberes_text());
		setSize(800, 624);
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
		cpUnidad.setSize("600", "130");

		gridObjEspecificos = new Grid<ObjetivoEspecificoModel>(
				new ListStore<ObjetivoEspecificoModel>(),
				getColumnModelObjEspecificos());
		gridObjEspecificos.setBorders(true);
		gridObjEspecificos.setStripeRows(true);
		gridObjEspecificos.setSize(250, 150);

		ContentPanel cpObjs = new ContentPanel();
		cpObjs.setBodyBorder(false);
		cpObjs.setHeading(MultiLingualConstants.tableObjEspecificos_heading());
		cpObjs.setButtonAlign(HorizontalAlignment.CENTER);
		cpObjs.setLayout(new FitLayout());
		cpObjs.setSize(250, 150);
		cpObjs.add(gridObjEspecificos);
		container.add(cpObjs, new AbsoluteData(100, 170));
		cpObjs.setSize("600", "130");

		gridSaberes = new Grid<SaberModel>(new ListStore<SaberModel>(),
				getColumnModelSaberes());
		gridSaberes.setBorders(true);
		gridSaberes.setStripeRows(true);

		ContentPanel cpSaberes = new ContentPanel();
		cpSaberes.setBodyBorder(false);
		cpSaberes.setHeading(MultiLingualConstants.tableSaberes_heading());
		cpSaberes.setButtonAlign(HorizontalAlignment.CENTER);
		cpSaberes.setLayout(new FitLayout());
		cpSaberes.setSize(250, 150);
		cpSaberes.add(gridSaberes);
		container.add(cpSaberes, new AbsoluteData(100, 310));
		cpSaberes.setSize("500", "130");

		gridMateriales = new Grid<RecursoModel>(new ListStore<RecursoModel>(),
				getColumnModelMaterial());
		gridMateriales.setBorders(true);
		gridMateriales.setStripeRows(true);

		ContentPanel cpMats = new ContentPanel();
		cpMats.setBodyBorder(false);
		cpMats.setHeading(MultiLingualConstants.tableRecursos_heading());
		cpMats.setButtonAlign(HorizontalAlignment.CENTER);
		cpMats.setLayout(new FitLayout());
		cpMats.setSize(250, 150);
		cpMats.add(gridMateriales);
		container.add(cpMats, new AbsoluteData(100, 450));
		cpMats.setSize("600", "130");

		btnAgregar = new Button(MultiLingualConstants.btnAgregar_text());
		container.add(btnAgregar, new AbsoluteData(630, 370));

		add(container);

		inicializarDialogos();

		eventoCargarTab();
		eventoSeleccionarObjEspecifico();
		eventoSeleccionarUnidad();
		eventoAgregarSaber();
		eventoEditarSaber();
		eventoSeleccionarSaber();
	}

	private void inicializarDialogos() {
		dialogoAgregarSaber = new DialogoAgregarSaber(this);
		dialogoEditarSaber = new DialogoEditarSaber(this);
	}

	private void eventoCargarTab() {
		this.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				cargarUnidades();
			}
		});
	}

	// nuevo
	private void cargarUnidades() {
		String codigoCurso = Registry.get("codigoCurso");
		listadosService.listarUnidadesPorCurso(codigoCurso,
				new AsyncCallback<List<UnidadBO>>() {
					@Override
					public void onSuccess(List<UnidadBO> result) {
						Dispatcher.forwardEvent(
								DTEvent.LISTAR_UNIDADES_SABERES, result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}

				});
	}

	private void eventoSeleccionarObjEspecifico() {
		gridObjEspecificos.getSelectionModel().addSelectionChangedListener(
				new SelectionChangedListener<ObjetivoEspecificoModel>() {
					@Override
					public void selectionChanged(
							SelectionChangedEvent<ObjetivoEspecificoModel> se) {
						idObjEspecifico = se.getSelectedItem().getId();
						cargarSaberesPorObjEspecifico();
					}
				});
	}

	private void eventoSeleccionarSaber() {
		gridSaberes.getSelectionModel().addSelectionChangedListener(
				new SelectionChangedListener<SaberModel>() {
					@Override
					public void selectionChanged(
							SelectionChangedEvent<SaberModel> se) {
						idSaber = se.getSelectedItem().getId();
						cargarRecursosPorSaber();
					}
				});
	}

	private void eventoSeleccionarUnidad() {
		gridUnidad.getSelectionModel().addSelectionChangedListener(
				new SelectionChangedListener<UnidadModel>() {
					@Override
					public void selectionChanged(
							SelectionChangedEvent<UnidadModel> se) {
						idUnidad = se.getSelectedItem().getId();
						cargarObjetivosEspecificos();
					}
				});
	}

	private void eventoAgregarSaber() {
		btnAgregar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (idObjEspecifico != 0) {
					dialogoAgregarSaber.show();
				} else {
					Info.display("sami",
							MultiLingualConstants.msgObjEspecifico());
				}
			}
		});
	}

	private void eventoEditarSaber() {
		gridSaberes.addListener(Events.CellDoubleClick,
				new Listener<GridEvent<SaberModel>>() {
					public void handleEvent(GridEvent<SaberModel> be) {
						SaberModel saber = be.getGrid().getSelectionModel()
								.getSelectedItem();
						dialogoEditarSaber.cargarDatos(saber);
						idSaber = saber.getId();
					}
				});
	}

	/*public void eventoAgregarRecurso() {
		btnAgregarRecurso.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (idSaber != 0) {
					
				} else {
					Info.display("sami",MultiLingualConstants.msgSaber());
				}
			}
		});
	}*/

	public void agregarSaber(String nombre, String tipo, String contenido) {
		SaberBO saber = new SaberBO();
		saber.setNombre(nombre);
		saber.setContenido(contenido);
		saber.setTipo(tipo);
		saber.setIdObjEspecifico(idObjEspecifico);

		planificadorService.agregarSaber(saber, new AsyncCallback<Integer>() {
			@Override
			public void onSuccess(Integer result) {
				cargarSaberesPorObjEspecifico();
				Info.display("sami", Mensajero.mostrarMensaje(result));
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", Mensajero.ON_FAILURE);
			}
		});
	}

	public void editarSaber(String nombre, String tipo, String contenido) {
		SaberBO saber = new SaberBO();
		saber.setId(idSaber);
		saber.setNombre(nombre);
		saber.setContenido(contenido);
		saber.setTipo(tipo);

		planificadorService.editarSaber(saber, new AsyncCallback<Integer>() {
			@Override
			public void onSuccess(Integer result) {

				cargarSaberesPorObjEspecifico();
				Info.display("sami", Mensajero.mostrarMensaje(result));
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", Mensajero.ON_FAILURE);
			}
		});
	}

	private void cargarObjetivosEspecificos() {
		listadosService.listarObjEspecificosPorUnidad(idUnidad,
				new AsyncCallback<List<ObjetivoEspecificoBO>>() {
					@Override
					public void onSuccess(List<ObjetivoEspecificoBO> result) {
						Dispatcher.forwardEvent(DTEvent.LISTAR_OBJ_ESPECIFICOS,
								result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}

				});
	}

	private void cargarSaberesPorObjEspecifico() {
		listadosService.listarSaberesPorObjetivoEspecifico(idObjEspecifico,
				new AsyncCallback<List<SaberBO>>() {
					@Override
					public void onSuccess(List<SaberBO> result) {
						Dispatcher.forwardEvent(
								DTEvent.LISTAR_SABERES_POR_OBJ_ESPECIFICO,
								result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}
				});
	}

	private void cargarRecursosPorSaber() {
		listadosService.listarRecursosPorSaber(idSaber,
				new AsyncCallback<List<RecursoBO>>() {

					@Override
					public void onFailure(Throwable arg0) {
						Info.display("Error", Mensajero.ON_FAILURE);

					}

					@Override
					public void onSuccess(List<RecursoBO> result) {
						Dispatcher.forwardEvent(
								DTEvent.LISTAR_RECURSOS_POR_SABER_EN_SABERES, result);

					}

				});
	}

	public void actualizarTablaObjEspecificos(
			ListStore<ObjetivoEspecificoModel> objModel) {
		gridObjEspecificos
				.reconfigure(objModel, getColumnModelObjEspecificos());

	}

	public void actualizarTablaUnidades(ListStore<UnidadModel> objModel) {
		// TODO Auto-generated method stub
		gridUnidad.reconfigure(objModel, getColumnModelUnidad());

	}

	public void actualizarTablaSaberes(ListStore<SaberModel> saberes) {
		gridSaberes.reconfigure(saberes, getColumnModelSaberes());
	}

	private ColumnModel getColumnModelObjEspecificos() {
		List<ColumnConfig> configsObj = new ArrayList<ColumnConfig>();

		ColumnConfig columnObj = new ColumnConfig("nombre",
				MultiLingualConstants.columnObjEspecificos_nombre(), 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsObj.add(columnObj);

		columnObj = new ColumnConfig("contenido",
				MultiLingualConstants.columnObjEspecificos_contenido(), 525);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsObj.add(columnObj);

		return new ColumnModel(configsObj);
	}

	private ColumnModel getColumnModelSaberes() {
		List<ColumnConfig> configSaberes = new ArrayList<ColumnConfig>();

		ColumnConfig columnSaberes = new ColumnConfig("nombre",
				MultiLingualConstants.columnSaberes_nombre(), 50);
		columnSaberes.setAlignment(HorizontalAlignment.LEFT);
		configSaberes.add(columnSaberes);

		columnSaberes = new ColumnConfig("tipo",
				MultiLingualConstants.columnSaberes_tipo(), 50);
		columnSaberes.setAlignment(HorizontalAlignment.LEFT);
		configSaberes.add(columnSaberes);

		columnSaberes = new ColumnConfig("contenido",
				MultiLingualConstants.columnSaberes_contenido(), 475);
		columnSaberes.setAlignment(HorizontalAlignment.LEFT);
		configSaberes.add(columnSaberes);

		return new ColumnModel(configSaberes);
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

	public void actualizarTablaRecursos(ListStore<RecursoModel> objModel) {
		gridMateriales.reconfigure(objModel, getColumnModelMaterial());

	}

	public void agregarRecursos(ListStore<RecursoModel> recursos) {
		
	}
}