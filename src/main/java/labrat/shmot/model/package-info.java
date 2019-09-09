@GenericGenerators({
        @GenericGenerator(name = "id_gena", strategy = "enhanced-sequence", parameters = {
                @Parameter(name = "sequence_name", value = "id_gena"),
                @Parameter(name = "initial_value", value = "5")
        })
})
        //todo заставить его работать, подозрение на баг, hiba не сбрасывает существующие последовательности + создает не сик-неймом а с ген-неймом
package labrat.shmot.model;

import org.hibernate.annotations.*;

//todo все енумы со стринг в ординал
//todo surround with add properties template