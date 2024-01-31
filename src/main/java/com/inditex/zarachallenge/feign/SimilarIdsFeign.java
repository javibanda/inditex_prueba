package com.inditex.zarachallenge.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@FeignClient(
        name = "similarProductIds",
        url = "localhost:3001"
)
public interface SimilarIdsFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/product/{id}/similarids")
    List<Long> getSimilarProductIds(@PathVariable("id") Long productId);
}
