module Items
  class ItemsFinderService < FinderService
    def prepare_resource
      resource = ItemPolicy::Scope.new(user, Item).resolve
                               .by_name_like(params[:name])
                               .by_item_type(params[:item_type])
                               .includes(%i[category sales_unit])
    end

  end
end
