package app.repository;

import app.projection.IdiomProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import app.entity.Idiom;

public interface IdiomRepository extends JpaRepository<Idiom,Long>
{
    Page<IdiomProjection> findIdsAndIdiomsByIdiomContainingIgnoreCase(Pageable pageable,String idiom);
}