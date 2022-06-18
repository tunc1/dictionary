package app.controller;

import app.entity.Idiom;
import app.projection.IdiomProjection;
import app.service.IdiomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class IdiomControllerTest
{
    @Mock
    IdiomService idiomService;
    IdiomController idiomController;

    @BeforeEach
    void setUp()
    {
        idiomController=new IdiomController(idiomService);
    }
    @Test
    void save()
    {
        Idiom idiom=new Idiom();
        Mockito.when(idiomService.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Idiom.class));
        Idiom saved=idiomController.save(idiom);
        Assertions.assertEquals(saved,idiom);
    }
    @Test
    void update()
    {
        Long id=Long.valueOf("1");
        Idiom idiom=new Idiom();
        Mockito.when(idiomService.update(Mockito.any())).thenAnswer(e->e.getArgument(0,Idiom.class));
        Idiom updated=idiomController.update(idiom,id);
        Assertions.assertEquals(updated,idiom);
        Assertions.assertEquals(updated.getId(),id);
    }
    @Test
    void deleteById()
    {
        idiomController.deleteById(Long.valueOf("1"));
        Mockito.verify(idiomService).deleteById(Mockito.any());
    }
    @Test
    void findById_returnsIdiom()
    {
        Idiom idiom=new Idiom();
        Mockito.when(idiomService.findById(Mockito.any())).thenReturn(idiom);
        Idiom actual=idiomController.findById(Long.valueOf("1"));
        Assertions.assertEquals(actual,idiom);
    }
    @Test
    void search()
    {
        IdiomProjection idiomProjection=Mockito.mock(IdiomProjection.class);
        Page<IdiomProjection> page=new PageImpl<>(List.of(idiomProjection));
        Mockito.when(idiomService.search(Mockito.any(),Mockito.any())).thenReturn(page);
        Page<IdiomProjection> actual=idiomController.search(PageRequest.of(0,20),"search");
        Assertions.assertEquals(actual,page);
    }
}