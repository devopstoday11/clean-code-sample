package at.pasztor.cleancodesample.common.controller;

import zone.refactor.spring.hateoas.contract.LinkProvider;
import zone.refactor.spring.hateoas.entity.LinkedEntity;
import zone.refactor.spring.hateoas.entity.SelfLink;

public class IndexResponse extends LinkedEntity<at.pasztor.cleancodesample.common.controller.IndexResponse.IndexLinks> {
    public IndexResponse(
        LinkProvider linkProvider
    ) {
        super(new IndexLinks(linkProvider));
    }

    public static class IndexLinks extends SelfLink {
        public IndexLinks(LinkProvider linkProvider) {
            super(linkProvider.getResourceLink(at.pasztor.cleancodesample.common.controller.IndexResponse.class));
        }
    }
}
