package app.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.SolrClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("Solr")
@ExtendWith(MockitoExtension.class)
public class SolrTests {

    @InjectMocks
    transient Solr solrConfig;

    @BeforeEach
    void beforeEach() {
        ReflectionTestUtils.setField(solrConfig, "solrURL", "http://localhost:8983/solr");
    }

    @Test
    @DisplayName("#solrClient returns a SolrClient instance")
    void solrClient() {
        assertThat(solrConfig.solrClient()).isInstanceOf(SolrClient.class);
    }

    @Test
    @DisplayName("#solrTemplate returns a SolrTemplate instance")
    void solrTemplate() {
        SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr").build();

        assertThat(solrConfig.solrTemplate(client)).isInstanceOf(SolrTemplate.class);
    }
}
