module Categories
  class CategoryCreatorService < CreatorService
    def prepare_resource
      Category.new(params_with_user)
    end
  end
end
