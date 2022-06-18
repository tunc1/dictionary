package app.controller;

import app.entity.Idiom;
import app.projection.IdiomProjection;
import app.service.IdiomService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/idiom")
public class IdiomController
{
	private IdiomService idiomService;
	public IdiomController(IdiomService idiomService)
	{
		this.idiomService=idiomService;
	}
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public Idiom save(@RequestBody Idiom idiom)
	{
		return idiomService.save(idiom);
	}
	@PutMapping("/{id}")
	public Idiom update(@RequestBody Idiom idiom,@PathVariable Long id)
	{
		idiom.setId(id);
		return idiomService.update(idiom);
	}
	@GetMapping("/{id}")
	public Idiom findById(@PathVariable Long id)
	{
		return idiomService.findById(id);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id)
	{
		idiomService.deleteById(id);
	}
	@GetMapping(params="search")
	public Page<IdiomProjection> search(Pageable pageable,String search)
	{
		return idiomService.search(pageable,search);
	}
}