package co.edu.unicesi.sami.client.home;

import java.util.ArrayList;
import java.util.List;

//import co.edu.unicesi.sami.bo.CEOTBO;
import co.edu.unicesi.sami.bo.CompetenciaBO;
import co.edu.unicesi.sami.bo.MetaTerminalBO;
import co.edu.unicesi.sami.bo.ObjetivoGeneralBO;
import co.edu.unicesi.sami.bo.ObjetivoTerminalBO;
import co.edu.unicesi.sami.bo.TrabajoAsignadoBO;
import co.edu.unicesi.sami.client.home.Mensajero;
import co.edu.unicesi.sami.client.competencias.CompetenciasService;
import co.edu.unicesi.sami.client.competencias.CompetenciasServiceAsync;
import co.edu.unicesi.sami.client.controller.DTEvent;
import co.edu.unicesi.sami.client.home.dialogos.DialogoAgregarObjTerminal;
import co.edu.unicesi.sami.client.home.dialogos.DialogoAsociarCompetencia;
import co.edu.unicesi.sami.client.home.dialogos.DialogoEditarObjTerminal;
import co.edu.unicesi.sami.client.listados.ListadosService;
import co.edu.unicesi.sami.client.listados.ListadosServiceAsync;
//import co.edu.unicesi.sami.client.model.CEOTModel;
import co.edu.unicesi.sami.client.model.CompetenciaModel;
import co.edu.unicesi.sami.client.model.MateriaModel;
import co.edu.unicesi.sami.client.model.MetaTerminalModel;
import co.edu.unicesi.sami.client.model.ObjetivoTerminalModel;
import co.edu.unicesi.sami.client.model.SaberModel;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TabObjTerminales extends TabItem {
	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);
	private final CompetenciasServiceAsync competenciasService = GWT
			.create(CompetenciasService.class);
	private final ListadosServiceAsync listadosService = GWT
			.create(ListadosService.class);

	private int idObjTerminal;
	private int idCompetencia;

	private DialogoAgregarObjTerminal dialogoAgregarObjTerminal;
	private DialogoEditarObjTerminal dialogoEditarObjTerminal;
	private DialogoAsociarCompetencia dialogoAsociarCompetencia;

	private Text labObjGeneral;
	private TextArea txtObjGeneral;
	private Button btnAgregar;
	private Button btnAsociarCompetencia;

	private Grid<ObjetivoTerminalModel> gridObjTerminales;
	private Grid<CompetenciaModel> gridCompetencias;

	public TabObjTerminales() {
		setText(MultiLingualConstants.tabObjTerminales_text());
		setSize(800, 600);
		LayoutContainer container = new LayoutContainer();
		container.setLayout(new AbsoluteLayout());

		labObjGeneral = new Text(MultiLingualConstants.labObjGeneral_text());
		container.add(labObjGeneral, new AbsoluteData(100, 30));

		txtObjGeneral = new TextArea();
		txtObjGeneral.setSize("600px", "150px");
		container.add(txtObjGeneral, new AbsoluteData(100, 50));

		btnAgregar = new Button(MultiLingualConstants.btnAgregar_text());
		container.add(btnAgregar, new AbsoluteData(275, 510));
		
		btnAsociarCompetencia = new Button("Asociar");
		container.add(btnAsociarCompetencia,new AbsoluteData(575,510));
		

		gridObjTerminales = new Grid<ObjetivoTerminalModel>(
				new ListStore<ObjetivoTerminalModel>(), getColumnModel());
		gridObjTerminales.setBorders(true);
		gridObjTerminales.setStripeRows(true);
		gridObjTerminales.setSize("430px", "275px");
				
		ContentPanel cpObjs = new ContentPanel();
		cpObjs.setBodyBorder(false);
		cpObjs.setHeading(MultiLingualConstants.tableObjTerminales_heading());
		cpObjs.setButtonAlign(HorizontalAlignment.CENTER);
		cpObjs.setLayout(new FitLayout());
		cpObjs.setSize(430, 275);
		cpObjs.add(gridObjTerminales);
		container.add(cpObjs, new AbsoluteData(100, 225));
		cpObjs.setSize("370", "275");

		add(container);
		
		gridCompetencias=new Grid<CompetenciaModel>(new ListStore<CompetenciaModel>(),getColumnCompetenciaModel());
		gridCompetencias.setBorders(true);
		gridCompetencias.setStripeRows(true);
		gridCompetencias.setSize("130px","275px");
		
		ContentPanel cpObjs2 = new ContentPanel();
		cpObjs2.setBodyBorder(false);
		cpObjs2.setHeading("Competencias");
		cpObjs2.setButtonAlign(HorizontalAlignment.CENTER);
		cpObjs2.setLayout(new FitLayout());
		cpObjs2.setSize(130, 275);
		cpObjs2.add(gridCompetencias);
		container.add(cpObjs2, new AbsoluteData(510, 225));
		cpObjs2.setSize("190", "275");

		add(container);


		inicializarDialogos();

		eventoCargarTab();
		eventoAgregarObjTerminal();
		eventoEditarObjTerminal();
		eventoAsociarCompetencia();
	}

	public void inicializarDialogos() {
		dialogoAgregarObjTerminal = new DialogoAgregarObjTerminal(this);
		dialogoEditarObjTerminal = new DialogoEditarObjTerminal(this);
		dialogoAsociarCompetencia=new DialogoAsociarCompetencia(this);
	}

	private void eventoCargarTab() {
		this.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				cargarObjGeneral();
				cargarObjTerminales();
				cargarCompetencias();
			}
		});
	}

	private void eventoAgregarObjTerminal() {
		btnAgregar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				dialogoAgregarObjTerminal.show();
			}
		});
	}

	private void eventoEditarObjTerminal() {
		gridObjTerminales.addListener(Events.CellDoubleClick,
				new Listener<GridEvent<ObjetivoTerminalModel>>() {
					@Override
					public void handleEvent(GridEvent<ObjetivoTerminalModel> e) {
						ObjetivoTerminalModel model = e.getGrid()
								.getSelectionModel().getSelectedItem();
						dialogoEditarObjTerminal.cargarDatos(model);
						idObjTerminal = model.getId();
						dialogoEditarObjTerminal.show();
					}
				});
	}
	private void eventoAsociarCompetencia(){
		btnAsociarCompetencia.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				dialogoAsociarCompetencia.show();
			}
		});
	}
	public void agregarObjTerminal(String nombre, String contenido) {
		ObjetivoTerminalBO objTerminal = new ObjetivoTerminalBO();
		objTerminal.setNombre(nombre);
		objTerminal.setContenido(contenido);
		String codigoCurso = Registry.get("codigoCurso");
		objTerminal.setCodigoCurso(codigoCurso);

		competenciasService.agregarObjTerminal(objTerminal,
				new AsyncCallback<Integer>() {
					@Override
					public void onSuccess(Integer result) {
						cargarObjTerminales();
						Info.display("sami", Mensajero.mostrarMensaje(result));
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}
				});
	}

	public void editarObjTerminal(String nombre, String contenido) {
		ObjetivoTerminalBO objTerminal = new ObjetivoTerminalBO();
		objTerminal.setNombre(nombre);
		objTerminal.setContenido(contenido);
		objTerminal.setId(idObjTerminal);

		competenciasService.editarObjTerminal(objTerminal,
				new AsyncCallback<Integer>() {
					@Override
					public void onSuccess(Integer result) {
						cargarObjTerminales();
						Info.display("sami", Mensajero.mostrarMensaje(result));
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}
				});
	}
//	private void asociarCompetencia(String introduce,String enseña,String aplica) {
//			CEOTBO ceot = new CEOTBO();
//			ceot.setIdObjTerminal(idObjTerminal);
//			ceot.setId(idCompetencia);
//			ceot.setIntroduce(introduce);
//			ceot.setEnseña(enseña);
//			ceot.setAplica(aplica);
//			competenciasService.agregarCEOT(ceot,
//					new AsyncCallback<Integer>() {
//						@Override
//						public void onSuccess(Integer result) {
//							//cargar
//						}
//
//						@Override
//						public void onFailure(Throwable caught) {
//							Info.display("Error", Mensajero.ON_FAILURE);
//						}
//					});
//	}
//	
//	private void eventoSeleccionarObjetivoTerminal( )
//    {
//        gridObjTerminales.addListener( Events.OnClick, new Listener<GridEvent<ObjetivoTerminalModel>>( )
//        {
//            public void handleEvent( GridEvent<ObjetivoTerminalModel> be )
//            {
//                ObjetivoTerminalModel model = be.getGrid( ).getSelectionModel( ).getSelectedItem( );
//                idObjTerminal = model.getId( );
//           }
//        } );
//    }
//	
//	private void eventoSeleccionarCompetencia(){
//		gridCompetencias.addListener(Events.OnClick,new Listener<GridEvent<CompetenciaModel>>(){
//			public void handleEvent(GridEvent<CompetenciaModel> be){
//				CompetenciaModel model=be.getGrid().getSelectionModel().getSelectedItem();
//				idCompetencia=model.getId();
//			}
//		});
//	}

	private void cargarObjGeneral() {
		String codigoCurso = Registry.get("codigoCurso");
		competenciasService.buscarObjGeneral(codigoCurso,
				new AsyncCallback<ObjetivoGeneralBO>() {
					@Override
					public void onSuccess(ObjetivoGeneralBO objGeneral) {
						if (objGeneral != null) {
							txtObjGeneral.setValue(objGeneral.getContenido());
						} else {
							txtObjGeneral.clear();
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}

				});

	}

	private void cargarObjTerminales() {
		String codigoCurso = Registry.get("codigoCurso");
		listadosService.listarObjTerminalesPorCurso(codigoCurso,
				new AsyncCallback<List<ObjetivoTerminalBO>>() {
					@Override
					public void onSuccess(List<ObjetivoTerminalBO> result) {
						Dispatcher.forwardEvent(DTEvent.LISTAR_OBJ_TERMINALES,
								result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}
				});
	}
	private void cargarCompetencias(){
		listadosService.listarCompetencias(new AsyncCallback<List<CompetenciaBO>>(){
			public void onSuccess(List<CompetenciaBO> result){
				Dispatcher.forwardEvent(DTEvent.LISTAR_COMPETENCIAS,result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Info.display("Error",Mensajero.ON_FAILURE);
				
			}
		});
	}

	public void actualizarTablaObjTerminales(
			ListStore<ObjetivoTerminalModel> objTerminales) {
		gridObjTerminales.reconfigure(objTerminales, getColumnModel());
	}
	public void actualizarCompetencias(ListStore<CompetenciaModel> competencias){
		gridCompetencias.reconfigure(competencias, getColumnCompetenciaModel());
	}

	private ColumnModel getColumnModel() {
		List<ColumnConfig> configsObjTerminales = new ArrayList<ColumnConfig>();

		ColumnConfig columnObj = new ColumnConfig("nombre",
				MultiLingualConstants.columnObjEspecificos_nombre(), 50);
		columnObj.setAlignment(HorizontalAlignment.CENTER);
		configsObjTerminales.add(columnObj);

		columnObj = new ColumnConfig("contenido",
				MultiLingualConstants.columnObjEspecificos_contenido(), 525);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsObjTerminales.add(columnObj);

		return new ColumnModel(configsObjTerminales);
	}
	private ColumnModel getColumnCompetenciaModel(){
		List<ColumnConfig> configsCompetencias = new ArrayList<ColumnConfig>();

		ColumnConfig columnObj = new ColumnConfig("nombre",
				"Nombre", 50);
		columnObj.setAlignment(HorizontalAlignment.CENTER);
		configsCompetencias.add(columnObj);

		columnObj = new ColumnConfig("descripcion",
				"Descripción", 525);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsCompetencias.add(columnObj);

		return new ColumnModel(configsCompetencias);
	}
}
