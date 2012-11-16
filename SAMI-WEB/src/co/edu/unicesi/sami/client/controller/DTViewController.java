package co.edu.unicesi.sami.client.controller;

import java.util.List;

import co.edu.unicesi.sami.bo.MateriaBO;
import co.edu.unicesi.sami.bo.MaterialBO;
import co.edu.unicesi.sami.bo.MetaTerminalBO;
import co.edu.unicesi.sami.bo.ObjetivoEspecificoBO;
import co.edu.unicesi.sami.bo.ObjetivoTerminalBO;
import co.edu.unicesi.sami.bo.RecursoBO;
import co.edu.unicesi.sami.bo.SaberBO;
import co.edu.unicesi.sami.bo.SesionBO;
import co.edu.unicesi.sami.bo.TrabajoAsignadoBO;
import co.edu.unicesi.sami.bo.UnidadBO;
import co.edu.unicesi.sami.client.home.PanelCursos;
import co.edu.unicesi.sami.client.home.TabMateriales;
import co.edu.unicesi.sami.client.home.TabObjEspecificos;
import co.edu.unicesi.sami.client.home.TabObjGeneral;
import co.edu.unicesi.sami.client.home.TabObjTerminales;
import co.edu.unicesi.sami.client.home.TabPlanificador;
import co.edu.unicesi.sami.client.home.TabRecursos;
import co.edu.unicesi.sami.client.home.TabSaberes;
import co.edu.unicesi.sami.client.home.TabUnidades;
import co.edu.unicesi.sami.client.model.MateriaModel;
import co.edu.unicesi.sami.client.model.MaterialModel;
import co.edu.unicesi.sami.client.model.MetaTerminalModel;
import co.edu.unicesi.sami.client.model.ObjetivoEspecificoModel;
import co.edu.unicesi.sami.client.model.ObjetivoTerminalModel;
import co.edu.unicesi.sami.client.model.RecursoModel;
import co.edu.unicesi.sami.client.model.SaberModel;
import co.edu.unicesi.sami.client.model.SesionModel;
import co.edu.unicesi.sami.client.model.TrabajoAsignadoModel;
import co.edu.unicesi.sami.client.model.UnidadModel;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class DTViewController extends Controller {
	public DTViewController() {
		registerEventTypes(DTEvent.LISTAR_CURSOS);
		registerEventTypes(DTEvent.ACTUALIZAR_OBJ_GENERAL);
		registerEventTypes(DTEvent.LISTAR_MATERIALES);
		registerEventTypes(DTEvent.LISTAR_METAS_TERMINALES);
		registerEventTypes(DTEvent.LISTAR_OBJ_TERMINALES);
		registerEventTypes(DTEvent.LISTAR_OBJ_ESPECIFICOS);
		registerEventTypes(DTEvent.LISTAR_OBJ_ESPECIFICOS_POR_META_TERMINAL);
		registerEventTypes(DTEvent.LISTAR_RECURSOS_POR_SABER);
		registerEventTypes(DTEvent.LISTAR_SABERES);
		registerEventTypes(DTEvent.LISTAR_SABERES_POR_OBJ_ESPECIFICO);
		registerEventTypes(DTEvent.SELECCIONAR_CURSO);
		registerEventTypes(DTEvent.LISTAR_UNIDADES);
		registerEventTypes(DTEvent.LISTAR_OBJ_TERMINALES_UNIDADES);
		registerEventTypes(DTEvent.LISTAR_OBJ_TERMINALES_POR_UNIDAD);
		registerEventTypes(DTEvent.LISTAR_UNIDADES_PLANIFICADOR);
		registerEventTypes(DTEvent.LISTAR_OBJ_ESPECIFICOS_PLANIFICADOR);
		registerEventTypes(DTEvent.LISTAR_SESIONES);
		registerEventTypes(DTEvent.LISTAR_UNIDADES_SABERES);
		registerEventTypes(DTEvent.LISTAR_RECURSOS_POR_SABER_EN_SABERES);
		registerEventTypes(DTEvent.LISTAR_RECURSOS_DIALOGO_AGREGAR_SESION);
		registerEventTypes(DTEvent.LISTAR_SESIONES_DIALOGO_GESTION_SESION);
		registerEventTypes(DTEvent.LISTAR_TRABAJOS_DIALOGO_GESTION_SESION);
	}

	@Override
	public void handleEvent(AppEvent event) {
		if (event.getType().equals(DTEvent.LISTAR_CURSOS)) {
			List<MateriaBO> cursos = (List<MateriaBO>) event.getData();
			ListStore<MateriaModel> cursosModel = new ListStore<MateriaModel>();

			for (MateriaBO bo : cursos) {
				cursosModel.add(MateriaModel.toModelFromBO(bo));
			}

			PanelCursos panelCursos = Registry.get("panelCursos");
			panelCursos.actualizarPanel(cursosModel);
		} else if (event.getType().equals(DTEvent.ACTUALIZAR_OBJ_GENERAL)) {
			TabObjGeneral tabObjGeneral = Registry.get("tabObjGeneral");
			tabObjGeneral.asignarObjGeneral();
		} else if (event.getType().equals(DTEvent.LISTAR_MATERIALES)) {
			List<MaterialBO> materiales = (List<MaterialBO>) event.getData();
			ListStore<MaterialModel> materialesModel = new ListStore<MaterialModel>();

			for (MaterialBO bo : materiales) {
				materialesModel.add(MaterialModel.toModelFromBO(bo));
			}

			TabMateriales tabMateriales = Registry.get("tabMateriales");
			tabMateriales.actualizarTablaMateriales(materialesModel);

			TabRecursos tabRecursos = Registry.get("tabRecursos");
			tabRecursos.actualizarTablaMateriales(materialesModel);
		} else if (event.getType().equals(DTEvent.LISTAR_METAS_TERMINALES)) {
			List<MetaTerminalBO> metasTerminales = (List<MetaTerminalBO>) event
					.getData();
			ListStore<MetaTerminalModel> metasModel = new ListStore<MetaTerminalModel>();

			for (MetaTerminalBO bo : metasTerminales) {
				metasModel.add(MetaTerminalModel.toModelFromBO(bo));
			}

			TabObjEspecificos tabObjEspecificos = Registry
					.get("tabObjEspecificos");
			tabObjEspecificos.actualizarTablaMetasTerminales(metasModel);
		} else if (event.getType().equals(DTEvent.LISTAR_OBJ_ESPECIFICOS)) {
			List<ObjetivoEspecificoBO> objEspecificos = (List<ObjetivoEspecificoBO>) event
					.getData();
			ListStore<ObjetivoEspecificoModel> objModel = new ListStore<ObjetivoEspecificoModel>();

			for (ObjetivoEspecificoBO bo : objEspecificos) {
				objModel.add(ObjetivoEspecificoModel.toModelFromBO(bo));
			}

			TabObjEspecificos tabObjEspecificos = Registry
					.get("tabObjEspecificos");
			tabObjEspecificos.actualizarTablaObjEspecificos(objModel);

			TabSaberes tabSaberes = Registry.get("tabSaberes");
			tabSaberes.actualizarTablaObjEspecificos(objModel);
		} else if (event.getType().equals(DTEvent.LISTAR_OBJ_TERMINALES)) {
			List<ObjetivoTerminalBO> objTerminales = (List<ObjetivoTerminalBO>) event
					.getData();
			ListStore<ObjetivoTerminalModel> objModel = new ListStore<ObjetivoTerminalModel>();

			for (ObjetivoTerminalBO bo : objTerminales) {
				objModel.add(ObjetivoTerminalModel.toModelFromBO(bo));
			}
			TabObjTerminales tabObjTerminales = Registry
					.get("tabObjTerminales");
			tabObjTerminales.actualizarTablaObjTerminales(objModel);
		} else if (event.getType().equals(
				DTEvent.LISTAR_OBJ_ESPECIFICOS_POR_META_TERMINAL)) {
			List<ObjetivoEspecificoBO> objEspecificos = (List<ObjetivoEspecificoBO>) event
					.getData();
			ListStore<ObjetivoEspecificoModel> objModel = new ListStore<ObjetivoEspecificoModel>();

			for (ObjetivoEspecificoBO bo : objEspecificos) {
				objModel.add(ObjetivoEspecificoModel.toModelFromBO(bo));
			}

			TabObjEspecificos tabObjEspecificos = Registry
					.get("tabObjEspecificos");
			tabObjEspecificos.actualizarTablaObjEspecificos(objModel);
		} else if (event.getType().equals(DTEvent.LISTAR_RECURSOS_POR_SABER)) {
			List<RecursoBO> recursos = (List<RecursoBO>) event.getData();
			ListStore<MaterialModel> materialesModel = new ListStore<MaterialModel>();

			for (RecursoBO bo : recursos) {
				materialesModel.add(MaterialModel.toModelFromBO(bo));
			}

			TabRecursos tabRecursos = Registry.get("tabRecursos");
			tabRecursos.actualizarTablaRecursos(materialesModel);
		} else if (event.getType().equals(DTEvent.LISTAR_SABERES)) {
			List<SaberBO> saberes = (List<SaberBO>) event.getData();
			ListStore<SaberModel> saberesModel = new ListStore<SaberModel>();

			for (SaberBO bo : saberes) {
				saberesModel.add(SaberModel.toModelFromBO(bo));
			}

			TabSaberes tabSaberes = Registry.get("tabSaberes");
			tabSaberes.actualizarTablaSaberes(saberesModel);

			TabRecursos tabRecursos = Registry.get("tabRecursos");
			tabRecursos.actualizarTablaSaberes(saberesModel);
		} else if (event.getType().equals(
				DTEvent.LISTAR_SABERES_POR_OBJ_ESPECIFICO)) {
			List<SaberBO> saberes = (List<SaberBO>) event.getData();
			ListStore<SaberModel> sModel = new ListStore<SaberModel>();

			for (SaberBO bo : saberes) {
				sModel.add(SaberModel.toModelFromBO(bo));
			}

			TabSaberes tabSaberes = Registry.get("tabSaberes");
			tabSaberes.actualizarTablaSaberes(sModel);
		} else if (event.getType().equals(DTEvent.SELECCIONAR_CURSO)) {
			MateriaModel cursoModel = (MateriaModel) event.getData();
			Registry.register("codigoCurso", cursoModel.getCodigo());

			TabPanel tabs = Registry.get("tabs");
			tabs.enable();

			TabObjGeneral tabObjGeneral = Registry.get("tabObjGeneral");
			tabObjGeneral.asignarObjGeneral();
			tabs.setSelection(tabObjGeneral);
		} else if (event.getType().equals(DTEvent.LISTAR_UNIDADES)) {
			List<UnidadBO> unidades = (List<UnidadBO>) event.getData();
			ListStore<UnidadModel> objModel = new ListStore<UnidadModel>();

			for (UnidadBO bo : unidades) {
				objModel.add(UnidadModel.toModelFromBO(bo));
			}
			TabUnidades tabUnidades = Registry.get("tabUnidades");
			tabUnidades.actualizarTablaUnidades(objModel);
		} else if (event.getType().equals(
				DTEvent.LISTAR_OBJ_TERMINALES_UNIDADES)) {
			List<ObjetivoTerminalBO> objTerminales = (List<ObjetivoTerminalBO>) event
					.getData();
			ListStore<ObjetivoTerminalModel> objModel = new ListStore<ObjetivoTerminalModel>();

			for (ObjetivoTerminalBO bo : objTerminales) {
				objModel.add(ObjetivoTerminalModel.toModelFromBO(bo));
			}

			TabUnidades tabUnidades = Registry.get("tabUnidades");
			tabUnidades.actualizarListaObjTerminales(objModel);
		} else if (event.getType().equals(
				DTEvent.LISTAR_OBJ_TERMINALES_POR_UNIDAD)) {
			List<ObjetivoTerminalBO> objTerminales = (List<ObjetivoTerminalBO>) event
					.getData();
			ListStore<ObjetivoTerminalModel> objModel = new ListStore<ObjetivoTerminalModel>();
			if (objTerminales != null) {
				for (ObjetivoTerminalBO bo : objTerminales) {
					objModel.add(ObjetivoTerminalModel.toModelFromBO(bo));
				}
				TabUnidades tabUnidades = Registry.get("tabUnidades");
				tabUnidades.actualizarListaObjTerminalesUnidad(objModel);
			}
		} else if (event.getType().equals(DTEvent.LISTAR_UNIDADES_PLANIFICADOR)) {
			List<UnidadBO> unidades = (List<UnidadBO>) event.getData();
			ListStore<UnidadModel> objModel = new ListStore<UnidadModel>();

			for (UnidadBO bo : unidades) {
				objModel.add(UnidadModel.toModelFromBO(bo));
			}
			TabPlanificador tabPlanificador = Registry.get("tabPlanificador");
			tabPlanificador.actualizarTablaUnidadesDisponibles(objModel);
		} else if (event.getType().equals(
				DTEvent.LISTAR_OBJ_ESPECIFICOS_PLANIFICADOR)) {
			List<ObjetivoEspecificoBO> objEsp = (List<ObjetivoEspecificoBO>) event
					.getData();
			ListStore<ObjetivoEspecificoModel> objModel = new ListStore<ObjetivoEspecificoModel>();

			for (ObjetivoEspecificoBO bo : objEsp) {
				objModel.add(ObjetivoEspecificoModel.toModelFromBO(bo));
			}
			TabPlanificador tabPlanificador = Registry.get("tabPlanificador");
			tabPlanificador.actualizarTablaObjetivosEspecificos(objModel);
		}else if (event.getType().equals(
				DTEvent.LISTAR_SESIONES)) {
			List<SesionBO> sesiones = (List<SesionBO>) event
					.getData();
			ListStore<SesionModel> objModel = new ListStore<SesionModel>();

			for (SesionBO bo : sesiones) {
				objModel.add(SesionModel.toModelFromBO(bo));
			}
			TabPlanificador tabPlanificador = Registry.get("tabPlanificador");
			tabPlanificador.actualizarTablaSesiones(objModel);
		}else if (event.getType().equals(
				DTEvent.LISTAR_UNIDADES_SABERES)) {
			List<UnidadBO> unidades = (List<UnidadBO>) event
					.getData();
			ListStore<UnidadModel> objModel = new ListStore<UnidadModel>();

			for (UnidadBO bo : unidades) {
				objModel.add(UnidadModel.toModelFromBO(bo));
			}
			TabSaberes tabSaberes = Registry.get("tabSaberes");
			tabSaberes.actualizarTablaUnidades(objModel);
		}else if (event.getType().equals(
				DTEvent.LISTAR_RECURSOS_POR_SABER_EN_SABERES)) {
			List<RecursoBO> recursos = (List<RecursoBO>) event
					.getData();
			ListStore<RecursoModel> objModel = new ListStore<RecursoModel>();

			for (RecursoBO bo : recursos) {
				objModel.add(RecursoModel.toModelFromBO(bo));
			}
			TabSaberes tabSaberes = Registry.get("tabSaberes");
			tabSaberes.actualizarTablaRecursos(objModel);
		}else if (event.getType().equals(
				DTEvent.LISTAR_RECURSOS_DIALOGO_AGREGAR_SESION)) {
			List<RecursoBO> recursos = (List<RecursoBO>) event
					.getData();
			ListStore<RecursoModel> objModel = new ListStore<RecursoModel>();

			for (RecursoBO bo : recursos) {
				objModel.add(RecursoModel.toModelFromBO(bo));
			}
			TabPlanificador tabPlanificador = Registry.get("tabPlanificador");
			tabPlanificador.actualizarTablaRecursos(objModel);
		}else if (event.getType().equals(
				DTEvent.LISTAR_RECURSOS_DIALOGO_AGREGAR_SESION)) {
			List<TrabajoAsignadoBO> trabajos = (List<TrabajoAsignadoBO>) event
					.getData();
			ListStore<TrabajoAsignadoModel> objModel = new ListStore<TrabajoAsignadoModel>();

			for (TrabajoAsignadoBO bo : trabajos) {
				objModel.add(TrabajoAsignadoModel.toModelFromBO(bo));
			}
			TabPlanificador tabPlanificador = Registry.get("tabPlanificador");
			tabPlanificador.actualizarTablaTrabajos(objModel);
		}else if (event.getType().equals(
				DTEvent.LISTAR_SESIONES_DIALOGO_GESTION_SESION)) {
			List<SesionBO> sesiones = (List<SesionBO>) event
					.getData();
			ListStore<SesionModel> objModel = new ListStore<SesionModel>();

			for (SesionBO bo : sesiones) {
				objModel.add(SesionModel.toModelFromBO(bo));
			}
			TabPlanificador tabPlanificador = Registry.get("tabPlanificador");
			tabPlanificador.actualizarTablaSesionesDialogo(objModel);
		}
		else if(event.getType().equals(DTEvent.LISTAR_TRABAJOS_DIALOGO_GESTION_SESION)){
			List<TrabajoAsignadoBO> trabajosAsignados=(List<TrabajoAsignadoBO>)event.getData();
			ListStore<TrabajoAsignadoModel> objModel=new ListStore<TrabajoAsignadoModel>();
			for(TrabajoAsignadoBO bo:trabajosAsignados){
				objModel.add(TrabajoAsignadoModel.toModelFromBO(bo));
			}
			TabPlanificador tabPlanificador=Registry.get("tabPlanificador");
			tabPlanificador.actualizarTablaTrabajos(objModel);
		}
	}
}
