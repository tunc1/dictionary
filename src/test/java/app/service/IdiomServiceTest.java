package app.service;

import app.entity.Idiom;
import app.projection.IdiomProjection;
import app.repository.IdiomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class IdiomServiceTest
{
    @Mock
    IdiomRepository idiomRepository;
    IdiomService idiomService;

    @BeforeEach
    void setUp()
    {
        idiomService=new IdiomService(idiomRepository);
    }
    @Test
    void save()
    {
        Idiom idiom=new Idiom();
        Mockito.when(idiomRepository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Idiom.class));
        Idiom saved=idiomService.save(idiom);
        Assertions.assertEquals(saved,idiom);
    }
    @Test
    void update()
    {
        Idiom idiom=new Idiom();
        Mockito.when(idiomRepository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Idiom.class));
        Idiom updated=idiomService.update(idiom);
        Assertions.assertEquals(updated,idiom);
    }
    @Test
    void deleteById()
    {
        idiomService.deleteById(Long.valueOf("1"));
        Mockito.verify(idiomRepository).deleteById(Mockito.any());
    }
    @Test
    void findById_returnsIdiom()
    {
        Idiom idiom=new Idiom();
        Mockito.when(idiomRepository.findById(Mockito.any())).thenReturn(Optional.of(idiom));
        Idiom actual=idiomService.findById(Long.valueOf("1"));
        Assertions.assertEquals(actual,idiom);
    }
    @Test
    void findById_throwsEntityNotFoundException()
    {
        Mockito.when(idiomRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class,()->idiomService.findById(Long.valueOf("1")));
    }
    @Test
    void search()
    {
        IdiomProjection idiomProjection=Mockito.mock(IdiomProjection.class);
        Page<IdiomProjection> page=new PageImpl<>(List.of(idiomProjection));
        Mockito.when(idiomRepository.findIdsAndIdiomsByIdiomContainingIgnoreCase(Mockito.any(),Mockito.any()))
                .thenReturn(page);
        Page<IdiomProjection> actual=idiomService.search(PageRequest.of(0,20),"search");
        Assertions.assertEquals(actual,page);
    }
}