module Categories
  class CategoriesFinderService < FinderService
    def prepare_resource
      CategoryPolicy::Scope.new(user, Category).resolve
                           .by_name_like(params[:name])
    end
  end
end
