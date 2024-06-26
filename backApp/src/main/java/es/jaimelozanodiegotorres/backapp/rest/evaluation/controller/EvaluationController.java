package es.jaimelozanodiegotorres.backapp.rest.evaluation.controller;

import es.jaimelozanodiegotorres.backapp.pagination.PageResponse;
import es.jaimelozanodiegotorres.backapp.rest.commons.controller.CommonController;
import es.jaimelozanodiegotorres.backapp.rest.evaluation.dto.EvaluationDto;
import es.jaimelozanodiegotorres.backapp.rest.evaluation.dto.EvaluationResponseDto;
import es.jaimelozanodiegotorres.backapp.rest.evaluation.filters.EvaluationFilters;
import es.jaimelozanodiegotorres.backapp.rest.evaluation.models.Evaluation;
import es.jaimelozanodiegotorres.backapp.rest.evaluation.services.EvaluationServicePgSqlImp;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador de la entidad Product
 * Anotación @RestController para indicar que es un controlador
 */
@RestController
@RequestMapping("evaluations")
@Slf4j
public class EvaluationController extends CommonController<Evaluation, Long, EvaluationDto>{
    EvaluationServicePgSqlImp service;

    /**
     * Constructor de la clase
     *
     * @param service Servicio de productos
     */
    @Autowired
    public EvaluationController(EvaluationServicePgSqlImp service) {
        this.service = service;
    }

    @Override
    @GetMapping("listAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Evaluation> listAll() {
        log.info("Listando todas las valoraciones");
        return service.listAll();
    }

    @GetMapping("listAll/{id}")
    public List<EvaluationResponseDto> listAllByProductId(@PathVariable Long id) {
        log.info("Listando todas las valoraciones de un producto");
        return service.findByProductId(id);
    }
/**
    @GetMapping("pageAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageResponse<Evaluation>> pageAll(@Valid EvaluationFilters filters) {
        return ResponseEntity.ok(service.pageAll(filters));
    }
    */
/**
    @GetMapping("pageAll/{id}")
    public ResponseEntity<PageResponse<Evaluation>> pageAllByProductId(@PathVariable Long id,@Valid EvaluationFilters filters) {
        return ResponseEntity.ok(service.findByProductId(id, filters));
    }
*/
    @Override
    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Evaluation> findById(@PathVariable Long id) {
        log.info("Buscando valoracion con id: {}", id);
        return ResponseEntity.ok(service.findById(id));
    }


    @PostMapping("saveEvaluation")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Evaluation> save(@RequestBody @Valid EvaluationDto dto) {
        log.info("Guardando valoracion");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @Override
    @PutMapping("updateEvaluation/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Evaluation> update(@PathVariable Long id, @RequestBody @Valid EvaluationDto dto) {
        log.info("Actualizando valoracion con id {} y datos: {}" , id, dto);
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Override
    @DeleteMapping("deleteEvaluation/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        log.info("Borrando valoracion con id {}", id);
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
