package com.doceria.service;

import com.doceria.dto.CategoriaDTO;
import com.doceria.model.Categoria;
import com.doceria.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaService {
    
    private final CategoriaRepository categoriaRepository;
    
    // MÉTODO PÚBLICO - PODE SER USADO POR OUTROS SERVICES
    public CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null) return null;
        
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .slug(categoria.getSlug())
                .descricao(categoria.getDescricao())
                .icone(categoria.getIcone())
                .ativo(categoria.getAtivo())
                .build();
    }
    
    // MÉTODO PÚBLICO - PARA CONVERTER LISTAS
    public List<CategoriaDTO> toDTOList(List<Categoria> categorias) {
        return categorias.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    // MÉTODO PRIVADO - SÓ PARA USO INTERNO
    private Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) return null;
        
        return Categoria.builder()
                .nome(dto.getNome())
                .slug(dto.getSlug())
                .descricao(dto.getDescricao())
                .icone(dto.getIcone())
                .ativo(dto.getAtivo() != null ? dto.getAtivo() : true)
                .build();
    }
    
    // BUSCA POR ID - RETORNA A ENTIDADE (PARA USO INTERNO)
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + id));
    }
    
    // MÉTODOS PÚBLICOS DA API
    public List<CategoriaDTO> listarTodas() {
        return toDTOList(categoriaRepository.findAll());
    }
    
    public List<CategoriaDTO> listarAtivas() {
        return toDTOList(categoriaRepository.findByAtivoTrue());
    }
    
    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = findById(id);
        return toDTO(categoria);
    }
    
    public CategoriaDTO buscarPorSlug(String slug) {
        Categoria categoria = categoriaRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com slug: " + slug));
        return toDTO(categoria);
    }
    
    @Transactional
    public CategoriaDTO criar(CategoriaDTO categoriaDTO) {
        if (categoriaRepository.existsBySlug(categoriaDTO.getSlug())) {
            throw new RuntimeException("Já existe uma categoria com este slug: " + categoriaDTO.getSlug());
        }
        
        Categoria categoria = toEntity(categoriaDTO);
        Categoria saved = categoriaRepository.save(categoria);
        log.info("Categoria criada: {}", saved.getNome());
        return toDTO(saved);
    }
    
    @Transactional
    public CategoriaDTO atualizar(Long id, CategoriaDTO categoriaDTO) {
        Categoria categoria = findById(id);
        
        if (!categoria.getSlug().equals(categoriaDTO.getSlug()) && 
            categoriaRepository.existsBySlug(categoriaDTO.getSlug())) {
            throw new RuntimeException("Este slug já está em uso: " + categoriaDTO.getSlug());
        }
        
        categoria.setNome(categoriaDTO.getNome());
        categoria.setSlug(categoriaDTO.getSlug());
        categoria.setDescricao(categoriaDTO.getDescricao());
        categoria.setIcone(categoriaDTO.getIcone());
        categoria.setAtivo(categoriaDTO.getAtivo());
        
        Categoria updated = categoriaRepository.save(categoria);
        log.info("Categoria atualizada: {}", updated.getNome());
        return toDTO(updated);
    }
    
    @Transactional
    public void deletar(Long id) {
        Categoria categoria = findById(id);
        categoriaRepository.delete(categoria);
        log.info("Categoria deletada: {}", categoria.getNome());
    }
}