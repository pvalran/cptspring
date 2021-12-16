package com.Xoot.CreditoParaTi.repositories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.logging.Logger;
import java.util.List;


@Repository
public class QueryDirectionsData {
    private final static Logger LOGGER = Logger.getLogger(QueryDirectionsData.class.getName());
    private final EntityManager entityManager;

    @SqlResultSetMapping(
            name = "lstDirections",
            entities = {
                    @EntityResult(
                            entityClass = QueryData.DocStatusMap.class,
                            fields = {
                                    @FieldResult(name = "idSuburb", column = "idSuburb"),
                                    @FieldResult(name = "code", column = "code"),
                                    @FieldResult(name = "name", column = "name"),
                                    @FieldResult(name = "zipCode", column = "zipCode"),
                                    @FieldResult(name = "typeSuburb", column = "typeSuburb"),
                                    @FieldResult(name = "countiesId", column = "countiesId"),
                                    @FieldResult(name = "cityId", column = "cityId"),
                                    @FieldResult(name = "stateCode", column = "stateCode"),
                                    @FieldResult(name = "countiesCode", column = "countiesCode"),
                                    @FieldResult(name = "citiesCode", column = "citiesCode"),
                                    @FieldResult(name = "state", column = "state"),
                                    @FieldResult(name = "county", column = "county"),
                                    @FieldResult(name = "city", column = "city")
                            }
                    )
            }
    )

    class LstDirections {
        private Integer idSuburb;
        private String code;
        private String name;
        private String zipCode;
        private String typeSuburb;
        private Integer countiesId;
        private Integer cityId;
        private String stateCode;
        private String countiesCode;
        private String citiesCode;
        private String state;
        private String county;
        private String city;
    }

    @Autowired
    public QueryDirectionsData(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    public List<QueryDirectionsData.LstDirections> findDocStatusMap(Integer creditID) {
        String sql = "SELECT ls.id as idSuburb,\n" +
                "\tls.code as code,\n" +
                "\tls.name as name,\n" +
                "    ls.zip_code as zipCode,\n" +
                "\tls.type_suburb as typeSuburb,\n" +
                "    ls.counties_id as countiesId,\n" +
                "    ls.city_id  as cityId,\n" +
                "    ls.state_code  as stateCode,\n" +
                "    ls.counties_code  as countiesCode,\n" +
                "    ls.cities_code as citiesCode, \n" +
                "\tlc.name as city,lcs.name  as county, ls2.name as state FROM locations_suburb ls \n" +
                "\tleft join locations_cities lc \n" +
                "\t\ton lc.code = ls.cities_code \n" +
                "\t\t\tand lc.counties_code = ls.counties_code \n" +
                "\t\t\tand lc.state_code  = ls.state_code\n" +
                "\tleft join locations_counties lcs \n" +
                "\t\ton lcs.code = ls.counties_code  \n" +
                "\t\t\tand lcs.state_id  = ls.state_code\n" +
                "\tleft join locations_states ls2 \n" +
                "\t\ton ls2.id = ls.state_code where zip_code = :zipcode ORDER by ls.name ";
        Query query = entityManager.createNativeQuery(sql,"lstDirections");
        List<QueryDirectionsData.LstDirections> lstDirections =  query.getResultList();
        return lstDirections;
    }
}
