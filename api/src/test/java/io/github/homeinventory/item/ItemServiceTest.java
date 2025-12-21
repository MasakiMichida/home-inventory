package io.github.homeinventory.item;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.mockito.junit.jupiter.MockitoExtension;

import io.github.homeinventory.dto.ItemRequestDto;
import io.github.homeinventory.service.ItemService;
import io.github.homeinventory.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    ItemRepository repository;

    @InjectMocks
    ItemService service;

    @Test
    void createItem_正常系_repository_insert() {
        var req = new ItemRequestDto("リンゴ", 1);

        when(repository.insert(req)).thenReturn(1);

        int result = service.createItem(req);
        assertThat(result).isEqualTo(1);
        verify(repository).insert(req);

    }

    @Test
    void createItem_名称異常系_repository_insert(){
        var req = new ItemRequestDto("",1);
        
        assertThatThrownBy(()->service.createItem(req))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("名称");

        verifyNoInteractions(repository);
    }

    @Test
    void createItem_数量異常系_repository_insert(){
        var req = new ItemRequestDto("fal",-1);

        assertThatThrownBy(()->service.createItem(req))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("数量");

        verifyNoInteractions(repository);

    }
}
