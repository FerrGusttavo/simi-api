package br.com.gustavo.simi.Application.Features.Preceptor.Presenters;

import br.com.gustavo.simi.Domain.Entities.Preceptor;

public record PreceptorPresenter(
        Long id,
        String name,
        String email,
        String crm
) {
    public static PreceptorPresenter fromEntity(Preceptor preceptor) {
        return new PreceptorPresenter(
                preceptor.getId(),
                preceptor.getUser() != null ? preceptor.getUser().getName() : null,
                preceptor.getUser() != null ? preceptor.getUser().getEmail() : null,
                preceptor.getCrm()
        );
    }
}

