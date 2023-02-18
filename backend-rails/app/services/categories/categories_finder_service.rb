module Categories
  class CategoriesFinderService < FinderService

    def prepare_resource
      resource = CategoryPolicy::Scope.new(user, Category).resolve
      .by_name_like(params[:name])
    end
  end
end
