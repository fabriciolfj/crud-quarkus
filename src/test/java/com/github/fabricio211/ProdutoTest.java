package com.github.fabricio211;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.fabricio211.domain.model.Produto;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

@DBRider
@QuarkusTest
@QuarkusTestResource(DataBaseLifeCycle.class)
public class ProdutoTest {

    @Test
    @DataSet("produtos1.yml")
    public void testUm() {
        Assert.assertEquals(1, Produto.count());
    }
}
