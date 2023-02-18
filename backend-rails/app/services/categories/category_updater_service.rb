module Categories
  class CategoryUpdaterService < UpdaterService
    def resource_class
      Category
    end
  end
end
