package app.service;

import app.projection.IdiomProjection;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import app.entity.Idiom;
import app.repository.IdiomRepository;
import javax.persistence.EntityNotFoundException;

@Service
public class IdiomService
{
	private IdiomRepository idiomRepository;
	public IdiomService(IdiomRepository idiomRepository)
	{
		this.idiomRepository=idiomRepository;
	}
	public Idiom save(Idiom idiom)
	{
		return idiomRepository.save(idiom);
	}
	@CachePut(value="idioms",key="#idiom.id")
	public Idiom update(Idiom idiom)
	{
		return idiomRepository.save(idiom);
	}
	@CacheEvict(value="idioms",key="#id")
	public void deleteById(Long id)
	{
		idiomRepository.deleteById(id);
	}
	@Cacheable(value="idioms",key="#id")
	public Idiom findById(Long id)
	{
		return idiomRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}
	public Page<IdiomProjection> search(Pageable pageable,String idiom)
	{
		return idiomRepository.findIdsAndIdiomsByIdiomContainingIgnoreCase(pageable,idiom);
	}
}